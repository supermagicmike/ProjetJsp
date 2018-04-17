<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Please login</title>
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" media="screen" href="css/style.css" type="text/css"/>
    </head>
    <body>
            <div class="login-page">
                <img id="logo" src="pictures/avatarr.jpeg" alt="" width="100" height="100">
                <div class="form">
                    <h1>Member login</h1>                    
                    <form class="login-form" action="<c:url value="LoginController" />" method="POST">                       
                        <input name='loginParam' type="username"  id="usern" placeholder="nom d'utilisateur" required/>              
                        <input name='passwordParam' type="password" id="passwd" placeholder="mot de passe" required/>
                        <input type='submit' name='action' value='log in'class="btn btn-lg btn-outline-secondary">                        
                    </form>
                </div>
                        <p id="dedicace">
                            @Projet Web 2018
                            Adechy Commerot Zanchi
                        </p>
            </div>       
    </body>
</html>


