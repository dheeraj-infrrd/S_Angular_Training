// update GA tracking Id
var gaTrackingId;

$(document).ready(function() {
	// Google Analytics tracking ID
	if (typeof gaTrackingId === 'undefined') {
		$.ajax({
			url : window.location.origin + "/fetchgatrackingid.do",
			type : "GET",
			dataType : "html",
			async : true,
			success : function(data) {
				gaTrackingId = data;
				
				// Google Analytics tracking code
				(function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
					(i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
					m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
					})(window,document,'script','//www.google-analytics.com/analytics.js','ga');

					ga('create', gaTrackingId, 'auto');
					ga('send', 'pageview');
			},
			error : function(e) {
				redirectErrorpage();
			}
		});
	}
});