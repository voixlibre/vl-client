$(document).ready(function(){

    $("#trump").click(function(){
        $(this).fadeToggle(5000);
    });

    $("#ongoingTable").slideUp("slow", 800);

    $("#comingSoonTable").slideUp(800);

    $("#ongoingTitle").mouseenter(function(){
        $("#ongoingTable").slideDown(800);
    });

    $("#ongoingTable").mouseleave(function(){
        $("#ongoingTable").slideUp(800);
    });

    $("#comingSoonTitle").mouseenter(function(){
        $("#comingSoonTable").slideDown(800);
    });

    $("#comingSoonTable").mouseleave(function(){
        $("#comingSoonTable").slideUp(800);
    });
});
