package com.ssafy.obosa.service;

import com.ssafy.obosa.model.common.DefaultRes;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.model.domain.VerificationToken;
import com.ssafy.obosa.repository.UserRepository;
import com.ssafy.obosa.repository.VerificationTokenRepository;
import com.ssafy.obosa.service.common.JwtService;
import com.ssafy.obosa.enumeration.ResponseMessage;
import com.ssafy.obosa.enumeration.StatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService
{
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final VerificationTokenRepository tokenRepository;

    public UserService(final UserRepository userRepository, final JwtService jwtService, final VerificationTokenRepository tokenRepository)
    {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
    }

    @Transactional(readOnly = true)
    public User getUser(final String verificationToken) {
        final VerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public VerificationToken getVerificationToken(final String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    public void saveRegisteredUser(final User user) {
        userRepository.save(user);
    }

    public void deleteUser(final User user) {
        final VerificationToken verificationToken = tokenRepository.findByUser(user);

        if (verificationToken != null) {
            tokenRepository.delete(verificationToken);
        }

        userRepository.delete(user);
    }

    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }

    public VerificationToken generateNewVerificationToken(final String existingVerificationToken) {
        VerificationToken vToken = tokenRepository.findByToken(existingVerificationToken);
        vToken.updateToken(UUID.randomUUID()
                .toString());
        vToken = tokenRepository.save(vToken);
        return vToken;
    }

    @Transactional(readOnly = true)
    public Optional<User> findUserByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserByUID(final int uid) {
        return userRepository.findByUid(uid);
    }

    public ResponseMessage validateVerificationToken(String token) {
        final VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return ResponseMessage.TOKEN_INVALID;
        }

        final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate()
                .getTime()
                - cal.getTime()
                .getTime()) <= 0) {
            tokenRepository.delete(verificationToken);
            return ResponseMessage.TOKEN_EXPIRED;
        }

        user.setState(true);
        // tokenRepository.delete(verificationToken);
        userRepository.save(user);
        return ResponseMessage.TOKEN_VALID;
    }

    private boolean emailExists(final String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Transactional(readOnly = true)
    public DefaultRes<List<User>>  readuserList()
    {
        final List<User> userList = userRepository.findAll();

        return DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, userList);
    }

    @Transactional(readOnly = true)
    public User getUserByJwtToken(String jwtToken)
    {
        String email = jwtService.decode(jwtToken);

        if(email == null) return null;

        if(userRepository.findByEmail(email).isPresent())
        {
            return userRepository.findByEmail(email).get();
        }
        return null;
    }
}
