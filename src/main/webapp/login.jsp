<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Please login</title>
        <link href="bootstrap.min.css" rel="stylesheet" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" media="screen" href="loginstyle.css" type="text/css"/>
    </head>
    <body>
        <main class="bd-masthead" id="content" role="main">
            <img id="logo" src="pictures/avatarr.jpeg" alt="" width="100" height="100">
            <div class="container">
                <div class="row align-items-center">

                    <div id="ident">
                        <h1 id="titre">Member login</h1>
                        <form action="<c:url value="LoginController" />" method="POST"> <!-- l'action par défaut est l'URL courant, qui va rappeler la servlet -->

                            <p id="conn">
                                <label for = "loginParam">login </label> : <input name='loginParam' type="username"  id="usern" placeholder="nom d'utilisateur" required/>
                                <span id="msgPasswd"></span>
                            </p>

                            <p>
                                <label for = "nom">password</label> : <input name='passwordParam' type="password" id="passwd" placeholder="mot de passe" required/>
                                <span id="msgPasswd"></span>
                            </p>


                            <input type='submit' name='action' value='login'class="btn btn-lg btn-outline-secondary">
                        </form>

                    </div>
                </div>

                <p id="dedicace">
                    Projet Web
                </p>

            </div>
        </main>
    </body>
</html>


