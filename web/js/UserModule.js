/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

class UserModule{
    
createUser(){
        
        let inputLogin = document.getElementById("inputLogin").value;
        let inputPassword = document.getElementById("inputPassword").value;
        let data = {
            "inputLogin":inputLogin,
            "inputPassword":inputPassword,
        };
        fetch("createUserJson",{"method":"POST",
                                "headers":{'Content-Type':'application/json:charset=utf-8'},
                                "body": JSON.stringify(data)
                                    })
                                    .then(response => {
                                        if(response.status >= 200 & response.status < 300){
                                            return Promise.resolve(response)
                                        }
                                    })
                                        .then(response => {
                                           return response.json()
                                           })
                                                   .catch((ex)=> console.log("fetch Exception",ex))
                                                   .then(function(response){
                                                       if(response === null || response === undefined){
                                                           document.getElementById("info").innerHTML=`
                                                           Не получены данные с сервера
                                                            `;
                                                       }else{
                                                           document.getElementById("info").innerHTML=`
                                                           Пользователь добавлен`;
                                                       }
                                                   }
                                                           );
 }
    
 addFormNewUser(){
    document.getElementById("contentPage").innerHTML=`
    <div class="container">
 <div class="row">
<div class="col-md-3"></div>
 <div class="col-md-offset-3 col-md-6">
    <form class="form-horizontal" action="createUsers" method="post">
        <span class="heading">Регистрация</span>
        <p>${info}</p>
        <div class="form-group">
        <input type="text" class="form-control" id="inputLogin" name="userlogin" placeholder="Логин: ">
        <i class="fa fa-user"></i>
        </div>
        <div class="form-group help">
        <input type="password" class="form-control" id="inputPassword" name="userpassword" placeholder="Password">
        <i class="fa fa-lock"></i>
     
        </div>
        <div class="form-group">
        
        <button type="submit" id="btnAddUser" class="btn btn-default">Зарегистрироваться</button>
        </div>
    </form>
 </div>

 </div><!-- /.row -->
</div><!-- /.container -->
`;
    document.getElementById("btnAddUser").addEventListener("click",function(e){
        e.preventDefault();
        userModule.createUser();
        
    });
}  
    
    
}
let userModule = new UserModule();
export {userModule};
