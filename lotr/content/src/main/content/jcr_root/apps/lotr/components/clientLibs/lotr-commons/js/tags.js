$(document).ready(
		function() {

			$.post("/bin/AllTags", {
				tagGroup : 'LOTR'
			}, function(data, status) {
				var jsonArray = JSON.parse(data);
				$('ul.tagCloud').html();
				for (var i = 0; i < jsonArray.length; ++i) {
					$('ul.tagCloud').append(
							'<li class="tag" tagid="LOTR:' + jsonArray[i].tagid
									+ '" width="' + 100 / jsonArray.length
									+ '%" ><center>' + jsonArray[i].title
									+ '</center></li>');
				}
				$('.tag').click(function() {
					$('#txtSearch')[0].value = $(this).attr('tagid');
					$('#searchForm').submit();
				});
			});
	$("#txtSearch").prop('disabled', true);
    $('ul.tagCloud').fadeOut();
    $('.hm').html('&nbsp;Search bar is disabled. Use the switch to enable it.&nbsp;');
		});