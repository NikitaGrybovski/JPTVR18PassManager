/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import {userModule} from './UserModule.js';

class AuthModule {

    
    login() {
        let inputLogin = document.getElementById("inputLogin").value;
        let inputPassword = document.getElementById("inputPassword").value;
        let credentials = {
            "inputLogin": inputLogin,
            "inputPassword": inputPassword,

        };
        fetch("loginUserJson", {
                "method": "POST",
                "headers": {
                    'Content-Type': 'application/json:charset=utf-8'
                },
                "body": JSON.stringify(credentials)
            })
            .then(response => {
                if (response.status >= 200 & response.status < 300) {
                    return Promise.resolve(response)
                }
            })
            .then(response => {
                return response.json()
            })
            .catch((ex) => console.log("fetch Exception", ex))
            .then(function (response) {
                if (response === null || response === undefined) {
                    document.getElementById("info").innerHTML = `
                                                           Не получены данные с сервера
                                                            `;
                } else {
                    document.getElementById("info").innerHTML = `
                                                           Вы авторизовались`;
                    sessionStorage.setItem('users', JSON.stringify(response.data));
                    authModule.authMenu();
                }
            });
    }
    showFormLogin() {
        document.getElementById("contentPage").innerHTML = `
<div class="container">
 <div class="row">
    <div class="col-md-3"></div>
        <div class="col-md-offset-3 col-md-6">
            <form class="form-horizontal" action="loginUserJson" method="post">
                <span class="heading">Авторизация</span>
                <p>${info}</p>
                <div class="form-group">
                <input type="text" class="form-control" id="inputLogin" name="login" placeholder="Логин: ">
                <i class="fa fa-user"></i>
                </div>
                <div class="form-group help">
                <input type="password" class="form-control" id="inputPassword" name="password" placeholder="Password">
                <i class="fa fa-lock"></i>
                <a href="#" class="fa fa-question-circle"></a>
                </div>
                <div class="form-group">
                <span class="text">Нет логина? <button id="btnReg" type="button">Зарегистрироваться</button>
                <button id="btnLogin"  type="button" class="btn btn-default">Войти</button>
                </div>
            </form>
        </div>
    </div><!-- /.row -->
</div><!-- /.container -->
`;
        document.getElementById("btnLogin").addEventListener("click", function (e) {
            e.preventDefault();
            authModule.login();

        });
        document.getElementById("btnReg").addEventListener("click", function (e) {
            e.preventDefault();
            userModule.addFormNewUser();

        });
        
        $('body').keypress(function(event) {
          if(event.which == 13) {
            authModule.login();
            return true;
            }
        console.log(event.keyCode);
        });
        
        
        
    }

    authMenu() {
        let users = null;
        if (sessionStorage.getItem('users') !== null) {
            users = JSON.parse(sessionStorage.getItem('users'));
        }
        if (users !== null) {
            document.getElementById("addResource").style.display = 'block';
            document.getElementById("addUser").style.display = 'block';
            document.getElementById("listResources").style.display = 'block';
            document.getElementById("listUsers").style.display = 'block';
            document.getElementById("systemIn").style.display = 'none';
            document.getElementById("systemOut").style.display = 'block';
        } else {
            document.getElementById("addResource").style.display = 'none';
            document.getElementById("addUser").style.display = 'none';
            document.getElementById("listResources").style.display = 'none';
            document.getElementById("listUsers").style.display = 'none';
            document.getElementById("systemIn").style.display = 'block';
            document.getElementById("systemOut").style.display = 'none';
        }
    }
    logout() {
        fetch("logoutUserJson", {
                "method": "POST",
                "headers": {
                    'Content-Type': 'application/json;charset=utf-8'
                },

            })
            .then(response => {
                if (response.status >= 200 & response.status < 300) {
                    return Promise.resolve(response)
                }
            })
            .then(response => {
                return response.json()
            })
            .catch((ex) => console.log("Fetch Exception", ex))
            .then(function (response) {
                if (response === null || response === undefined) {
                    document.getElementById("info").innerHTML = 'Не получены данные';
                } else {
                    document.getElementById("info").innerHTML = response.info;
                    document.getElementById("contentPage").innerHTML = '';
                    if (sessionStorage.getItem('users')) {
                        sessionStorage.removeItem('users');
                    }
                    authModule.authMenu();
                }
            });
    }


}
let authModule = new AuthModule();
export {
    authModule
};
