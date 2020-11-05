/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import {
    authModule
} from './AuthModule.js';

class ResourceModule {

    createResource() {
        let inputName = document.getElementById("inputName").value;
        let inputUrl = document.getElementById("inputUrl").value;
        let inputLogin = document.getElementById("inputLogin").value;
        let inputPassword = document.getElementById("inputPassword").value;
        let data = {
            "inputName": inputName,
            "inputUrl": inputUrl,
            "inputLogin": inputLogin,
            "inputPassword": inputPassword,
        };
        fetch("createResourceJson", {
                "method": "POST",
                "headers": {
                    'Content-Type': 'application/json:charset=utf-8'
                },
                "body": JSON.stringify(data)
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
                                                           Ресурс добавлен`;
                }
            });
    }

    addFormNewResource() {
        document.getElementById("contentPage").innerHTML = `
    <div class="container">
 <div class="row">
     <div class="col-md-3"></div>
    <div class="col-md-offset-3 col-md-6">
        <form class="form-horizontal" action="createResource" method="post">
            <span class="heading">Добавить ресурс</span>
            <div class="form-group">
            <input type="text" class="form-control" id="inputName" name="name" placeholder="Имя ресурса:" >
            <i class="fab fa-adn"></i>
            
            </div>
            <div class="form-group help">
            <input type="text" class="form-control" id="inputUrl" name="url" placeholder="Адрес ресурса:">
            <i class="fa fa-id-badge"></i>
            
            </div>
            <div class="form-group help">
            <input type="text" class="form-control" id="inputLogin" name="login" placeholder="Логин ресурса:">
            <i class="fa fa-user"></i>
            
            </div>
            <div class="form-group help">
            <input type="text" class="form-control" id="inputPassword" name="password" placeholder="Пароль ресурса:">
            <i class="fa fa-lock"></i>
            
            </div>
            <div class="form-group">
          
            <button id="btnAddResource" type="button" class="btn btn-default">Создать</button>
            </div>
        </form>
    </div>

 </div><!-- /.row -->
</div><!-- /.container -->
`;
        document.getElementById("btnAddResource").addEventListener("click", function (e) {
            e.preventDefault();
            resourceModule.createResource()

        });
    }
    showListResources() {
        let users = JSON.parse(sessionStorage.getItem('users'));
        if (users === null) {
            document.getElementById("info").innerHTML = "Войдите в систему";
            authModule.showFormLogin;
            return;
        }
        fetch("getListResourcesJson", {
                "method": "POST",
                "headers": {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                "body": JSON.stringify(users)
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
                    document.getElementById("info").innerHTML = `Не получены данные`;
                } else {
                    document.getElementById("info").innerHTML = `Список ресурсов доставлен`;
                    document.getElementById("info").innerHTML = ``;
                    
                    resourceModule.createSelectResources(response.listResources);
                }
            });
    }
    createSelectResources(listResources) {

        let select = document.createElement('select');
        select.setAttribute("id", 'resourceId');
        select.setAttribute("class", 'custom-select w-50 mx-auto');
        console.log(listResources);

        for (var i = 0; i < listResources.length; i++) {
            select.options[select.options.length] = new Option(listResources[i].name, listResources[i].id);
        }
        document.getElementById("contentPage").insertAdjacentHTML('afterbegin',
            `<h3 class="w-100 text-center ">Список ресурсов:</h3>
    <div id="selectListResources" class="form-group w-50 mx-auto text-center">
    </div>`);
        let selectListResources = document.getElementById('selectListResources');
        while (selectListResources.firstChild) {
            selectListResources.removeChild(selectListResources.firstChild);
        }
        selectListResources.appendChild(select);
        select.selectedIndex = -1;
        select.addEventListener("change", function (e) {
            e.preventDefault();
            resourceModule.printResource(listResources);
        });
    }
    printResource(listResources) {
        let resourceId = document.getElementById('resourceId').value;
        let resource = {};
        for (let i = 0; i < listResources.length; i++) {
            if (listResources[i].id == resourceId) {
                resource = listResources[i];
            }
        }
        document.getElementById("contentPage").innerHTML =
            ` <h3 class="w-100 text-center ">${resource.name}</h3>
              <div class="form-group w-50 mx-auto">    
                  <label for="url">Url ресурса</label>
                  <input value="${resource.url}" type="text" class="form-control" id="url" name="url" aria-describedby="urlHelp" placeholder="url">
                  <small id="urlHelp" class="form-text text-muted"></small>
              </div>
              <div class="form-group w-50 mx-auto">    
                  <label for="login">Логин</label>
                  <input value="${resource.login}" type="text" class="form-control" id="login" name="login" aria-describedby="loginHelp" placeholder="Логин">
                  <small id="emailHelp" class="form-text text-muted"></small>
              </div>
              <div class="form-group w-50 mx-auto">    
                  <label for="password">Пароль</label>
                  <input value="${resource.password}" type="text" class="form-control" id="password" name="password" aria-describedby="passwordHelp" placeholder="Пароль">
                  <small id="emailHelp" class="form-text text-muted"></small>
              </div>
              <div class="form-group w-50 mx-auto text-center">
                  <button id="btnChangeResource" type="button" class="btn btn-primary w-50 mt-4">Изменить ресурс</button>
              </div>
              <div id="btnSaveResource" class="form-group w-50 mx-auto text-center display-none">
                  <button id="btnSaveResource" type="button" class="btn btn-primary w-50 mt-4">Сохранить ресурс</button>
              </div>`;
              document.getElementById('btnChangeResource').addEventListener("click", resourceModule.doEnabledInputs);          
              document.getElementById('btnSaveResource').addEventListener("click", resourceModule.saveResource); 
              resourceModule.accessToControll(false);
    }
    doEnabledInputs(){
        resourceModule.accessToControll(true);
      }
    accessToControll(enabled){
        if(enabled){
          document.getElementById("url").readOnly=false;
          document.getElementById("login").readOnly=false;
          document.getElementById("password").readOnly=false;
          document.getElementById("btnSaveResource").style.display = "block";
          document.getElementById("btnChangeResource").style.display = "none";
        }else{
          document.getElementById("url").readOnly=true;
          document.getElementById("login").readOnly=true;
          document.getElementById("password").readOnly=true;
          document.getElementById("btnSaveResource").style.display = "none";
          document.getElementById("btnChangeResource").style.display = "block";
        }
      }
    saveResource(){
        alert("Edit resource!");
        resourceModule.accessToControll(false);
      }


}
let resourceModule = new ResourceModule();
export {
    resourceModule
};