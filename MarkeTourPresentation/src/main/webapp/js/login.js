/**
 * Created by Andres on 28/04/2015.
 */
var baseLoguedHtml =
'<ul class="visible-lg visible-md"> ' +
    '<li style="border-left: 0px solid #eee;" class="pull-left"> ' +
        '<a href="/index.html" style="line-height: 40px; text-transform: none; color: #ffffff"> ' +
            '<i class="fa fa-user"></i> <span id="username">&nbsp;Andrés Decastro</span> ' +
        '</a>' +
    '</li> ' +
    '<li style="border-left: 0px solid #eee;"> ' +
        '<a href="/payment/ShoppingCart.html" style="line-height: 40px;   text-transform: none; color: #ffffff" > ' +
            '<i class="fa fa-shopping-cart"></i> <span id="shoppingcart">&nbsp;Carrito: 0 item(s) $0.00</span> ' +
        '</a> ' +
    '</li> ' +
    '<li style="border-left: 0px solid #eee;" class="pull-right"> ' +
        '<a href="index.html" style="line-height: 40px;   text-transform: none; color: #ffffff" > ' +
            '<i class="fa fa-sign-out"></i> <span id="signout">&nbsp;Sign out</span> ' +
        '</a> ' +
    '</li> ' +
'</ul> ' +
'<a href="#" class="toggle-menu visible-sm visible-xs"> <i class="fa fa-bars"></i></a>';

var baseNoLoguedHtml =
    '<ul class="visible-lg visible-md"> ' +
        '<li style="border-left: 0px solid #eee;" class="pull-right"> ' +
            '<a href="signin.html" style="line-height: 40px;   text-transform: none; color: #ffffff" > ' +
                '<i class="fa fa-user"></i> <span id="login">Login</span> ' +
            '</a> ' +
        '</li> ' +
    '</ul> ' +
    '<a href="#" class="toggle-menu visible-sm visible-xs"> <i class="fa fa-bars"></i></a>';

 if(window.sessionStorage.getItem("usuario")){
     $("#login-div").append(baseLoguedHtml);
     $("#username").text(sessionStorage.getItem("username"));
 }else{
     $("#login-div").append(baseNoLoguedHtml);
 }

$("#signout").click(function () {
    sessionStorage.clear();
    location.href = "/index.html"
});
