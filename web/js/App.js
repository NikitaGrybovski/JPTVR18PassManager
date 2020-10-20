/* 
 *Главный файл приложения на JavaScript
 */

document.getElementById("systemIn").onclick = function(e){
    e.preventDefault()
   toogleMenuActive("systemIn");
   
}
document.getElementById("addResource").onclick = function(e){
   e.preventDefault()
   toogleMenuActive("addResource");
   addFormNewResource();
}
document.getElementById("addUser").onclick = function(e){
    e.preventDefault()
   toogleMenuActive("addUser");
   addUser();
}
document.getElementById("systemOut").onclick = function(e){
   e.preventDefault()
   toogleMenuActive("systemOut");
}
document.getElementById("listResources").onclick = function(e){
   e.preventDefault()
   toogleMenuActive("listResources");
}
document.getElementById("listUsers").onclick = function(e){
    e.preventDefault()
   toogleMenuActive("listUsers");
}
document.getElementById("systemOut").onclick = function(e){
    e.preventDefault()
   toogleMenuActive("systemOut");
}
function toogleMenuActive(elementId){
    let activeElement = document.getElementById(elementId);
    let passiveElement = document.getElementsByClassName("nav-item");
    for(let i=0;i<passiveElement.lenght; i++){
        if(activeElement === passiveElement[i]){
            passiveElement[i].classList.add("active");
        }else{
            if(passiveElement[i].classList.cotains("active")){
                passiveElement[i].classList.remove("active");
            }
        }
    }
}

function addFormNewResource(){
    document.getElementById("contentPage").innerHTML=`
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
    document.getElementById("btnAddResource").addEventListener("click",function(e){
        e.preventDefault();
        createResource();
        
    });
    function createResource(){
        let name = document.getElementById("inputName").value;
        let url = document.getElementById("inputUrl").value;
        let login = document.getElementById("inputLogin").value;
        let password = document.getElementById("inputPassword").value;
        let data = {
            "inputName":inputName,
            "inputUrl":inputUrl,
            "inputLogin":inputLogin,
            "inputPassword":inputPassword,
        };
        fetch("createResourceJson",{"method":"POST",
                                "headers":{'Content-Type':'application/json:charset=utf-8'},
                                "body": JSON.stringify(data)
                                    })
                                    .then(response => {
                                        if(response.status >= 200 & response.status < 300){
                                            return Promise.resolve(response)
                                        }})
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
                                                           Ресурс`+response.data.inputName+ `добавлен`;
                                                       }
                                                   });
    }
}

function addUser(){
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

               <button type="submit" class="btn btn-default">Зарегистрироваться</button>
               </div>
           </form>
        </div>

    </div><!-- /.row -->
    </div><!-- /.container -->
`;
    
}
