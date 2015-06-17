console.log("Wiki Requested");

var hiddenTitle="";

$(document).ready(function(){
	if($('.hiddenTitle').length)
		hiddenTitle=$('.hiddenTitle').html();
	console.log("Title obtained = "+hiddenTitle);
	if(	$('.wikiContent').length ){
		console.log("Inititaing Wiki fetch...");
		$('.wikiContent').load('/bin/WikiContent?query='+encodeURI(hiddenTitle.split(" ")[0]));
		console.log("Wiki fetch completed or timed out");
	}
});