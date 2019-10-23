export default {
    findById: function(id, done) {
        $.get(API_BASE_URL + "/api/members/" + id).done(done);
    },
    signUp: function(regiFormData) {
        return $.ajax({
            type: "POST",
            url: API_BASE_URL + "/signup",
            data: regiFormData,
            processData: false,
            contentType: false,
            // success: function(response) {
            //     callback(response);
            // }
        });
    },
    login: function(email, password, callback, whenError) {
        var body = {
            "이메일": email,
            "비밀번호": password
        }

        $.ajax({
            type: 'POST',
            url: API_BASE_URL + "/api/members/login",
            data: JSON.stringify(body),
            headers: {
                'Content-Type': 'application/json'
            },
            success: callback,
            error: whenError
        });
    },
    logout: function(callback) {
        callback();
    },
    update: function(body, callback) {
        $.ajax({
            type: 'PUT',
            url: API_BASE_URL + "/api/members",
            data: JSON.stringify(body),
            headers: {
                'Content-Type': 'application/json'
            },
            success: callback
        })
    },
    duplicateEmail: function(email) {
        return $.ajax({
            type: 'GET',
            url: API_BASE_URL + "/signup/duplicateEmail",
            data: {
                'email': email
            }
        })
    },
    duplicateNickname: function(nickname) {
        return $.ajax({
            type: 'GET',
            url: API_BASE_URL + "/",
            // TODO : 서버에 닉네임 중복검사 요청
            // url: API_BASE_URL + "/signup/duplicateNickname",
            data: {
                'nickname': nickname
            }
        })
    }
}