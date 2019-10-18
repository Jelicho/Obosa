export default{
    findAll: function(callback){
        $.get(API_BASE_URL + "/api/products", function(data){
            callback(data);
        });
    },
    findProductsByOwner: function(userId, callback){
        $.get(API_BASE_URL + '/api/products/owner/' + userId, function(data){
            callback(data);
        });
    },
    findById: function(productId, callback){
        $.get(API_BASE_URL + "/api/products/" + productId, function(data){
            callback(data);
        });
    },
    findHistoryById: function(productId, callback){
        $.get(API_BASE_URL + "/api/products/history/" + productId, function(data){
            var result = [];

            function loadUser(from, until){
                if(from == until) {
                    callback(result);
                } else {
                    var history = data[from];

                    this.$userService.findById(history.owner, function(user){
                        result.push({
                            createdAt: history.createdAt,
                            owner: user['이름'] + " (" + user['이메일']+ ")"
                        });

                        loadUser(from+1, until);
                    });
                }
            }

            if(data) {
                loadUser(0, data.length);
            }
        });
    },
    create: function(body, success, whenError){
        $.ajax({
            type: 'POST',
            url: API_BASE_URL + '/api/products',
            data: JSON.stringify(body),
            headers: { 'Content-Type': 'application/json' },
            success: success,
            error: whenError
        })
    },
    update: function(body, success, whenError){
        $.ajax({
            type: 'PUT',
            url: API_BASE_URL + '/api/products',
            data: JSON.stringify(body),
            headers: { 'Content-Type': 'application/json' },
            success: success,
            error: whenError
        })
    },
    delete: function(id, success, whenError){
        $.ajax({
            type: 'DELETE',
            url: API_BASE_URL + "/api/products/" + id,
            success: success,
            error: whenError
        });
    }
}
