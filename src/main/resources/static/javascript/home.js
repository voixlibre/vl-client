$(document).ready(function(){

    $("#trump").click(function(){
        $(this).fadeToggle(5000);
    });

    $("#ongoingTable").slideUp();

    $("#comingSoonTable").slideUp();

    $("#ongoingTitle").mouseenter(function(){
        $("#ongoingTable").slideDown();
    });

    $("#ongoingTable").mouseleave(function(){
        $("#ongoingTable").slideUp();
    });

    $("#comingSoonTitle").mouseenter(function(){
        $("#comingSoonTable").slideDown();
    });

    $("#comingSoonTable").mouseleave(function(){
        $("#comingSoonTable").slideUp();
    });
});
