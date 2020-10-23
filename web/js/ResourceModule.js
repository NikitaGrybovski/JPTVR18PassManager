/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


class ResourceModule{
    
createResource(){
        let inputName = document.getElementById("inputName").value;
        let inputUrl = document.getElementById("inputUrl").value;
        let inputLogin = document.getElementById("inputLogin").value;
        let inputPassword = document.getElementById("inputPassword").value;
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
                                                           Ресурс добавлен`;
                                                       }
                                                   }
                                                           );
 }
    
 addFormNewResource(){
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
        resourceModule.createResource()
        
    });
}  
    
    
}
let resourceModule = new ResourceModule();
export {resourceModule};