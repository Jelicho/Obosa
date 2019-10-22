export default{
    findById: function(id, done){
        $.get(API_BASE_URL + "/api/members/" + id).done(done);
    },
    signUp: function(email, name, password, callback){
        var body = {
            "이메일": email,
            "이름": name,
            "비밀번호": password
        }

        $.ajax({
            type: "POST",
            url: API_BASE_URL + "/api/members",
            data: JSON.stringify(body),
            headers: { 'Content-Type': 'application/json' },
            success: function(response){
                callback(response);
            }
        });
    },
    login: function(email, password, callback, whenError){
        var body = {
            "email": email,
            "password": password
        }

        $.ajax({
            type: 'POST',
            // url: API_BASE_URL + "/api/members/login",
            url: 'localhost:8083/auth/login',
            data: JSON.stringify(body),
            headers: { 'Content-Type': 'application/json' },
            success: callback,
            error: whenError
        });
    },
    logout: function(callback){
        callback();
    },
    update: function(body, callback){
        $.ajax({
            type: 'PUT',
            url: API_BASE_URL + "/api/members",
            data: JSON.stringify(body),
            headers: { 'Content-Type': 'application/json' },
            success: callback
        })
    }
}
