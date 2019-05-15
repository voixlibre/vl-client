$(document).ready(function(){

    // jQuery methods go here...
    $("li").fadeOut(5000);
    //$("#trump").onclick(this, fadeOut(5000);
    $("#trump").click(function(){
        $(this).fadeOut(5000);
    });
});
