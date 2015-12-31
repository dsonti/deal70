var getCode = '<div class="store-listing-item shadow-box"><div class="store-thumb-link"><div class="store-thumb"><a href="#DOMAIN_NAME#store/#STORENAME#"><img src="#COUPON_THUMB#" alt=""></a></div><div class="store-name"><a href="#DOMAIN_NAME#store/#STORENAME#">#STORENAME_DISPLAY#<i class="angle right icon"></i></a></div></div><div class="latest-coupon"><h3 class="coupon-title"><a href="#">#COUPON_TITLE#</a></h3><div class="coupon-des">#COUPON_DESCRIPTION1#<span class="des-more">... <a href="#">More<i class="angle down icon"></i></a></span><div class="coupon-des-full" style="display: none;">#COUPON_DESCRIPTION2#<a class="des-less" href="#">Less<i class="angle up icon"></i></a></div></div></div><div class="coupon-detail coupon-button-type"><a href="#" class="coupon-button coupon-code" data-aff-url="#"><span class="code-text">#COUPON_CODE#</span><span class="get-code">Get Code</span></a><div class="exp-text">Expires #COUPON_EXPIRY_DATE#<a title="Save This Coupon" href="#" class="coupon-save"><i class="empty star icon"></i></a></div></div><!-- Coupon Modal --><div id="#COUPON_CODE_ID#" class="ui modal coupon-modal coupon-code-modal"><div class="coupon-header clearfix"><div class="coupon-store-thumb"><img src="thumb/stores/vientohotel.png" alt=""></div><div class="coupon-title">#COUPON_TITLE#</div><span class="close"></span></div><div class="coupon-content"><p class="coupon-type-text">Copy this code and use at checkout</p><div class="modal-code"><span class="code-text">#COUPON_CODE#</span></div><div class="clearfix"><div class="user-ratting ui icon basic buttons"><div class="ui button icon-popup" data-offset="0" data-content="This worked" data-variation="inverted"><i class="smile icon"></i></div><div class="ui button icon-popup" data-offset="0" data-content="It did not work" data-variation="inverted"><i class="frown icon"></i></div><div class="save-coupon ui button icon-popup" data-offset="0" data-content="Save this coupon" data-variation="inverted"><i class="empty star icon"></i></div></div><a href="#STORE_URL#" target="_blank" class="ui button btn btn_secondary go-store">Go To Store<i class="angle right icon"></i></a></div><div class="clearfix"><span class="user-ratting-text">Did it work?</span><span class="show-detail"><a href="#">Coupon Detail<i class="angle down icon"></i></a></span></div><div class="coupon-popup-detail"><p>#COUPON_POPUP_DETAIL#<p><p><strong>Expires</strong>: #COUPON_EXPIRY_DATE#</p><p><strong>Submitted</strong>: #COUPON_SUBMITTED#</p></div></div><div class="coupon-footer"><ul class="clearfix"><li><span><i class="wifi icon"></i> #TOTAL_COUNT# People Used, #TODAYS_COUNT# Today</span></li><li><a class="modal-share" href="#"><i class="share alternate icon"></i> Share</a></li></ul><div class="share-modal-popup ui popup"><a class="tiny ui facebook button"><i class="facebook icon"></i>Facebook</a><a class="tiny ui twitter button"><i class="twitter icon"></i>Twitter</a><a class="tiny ui google plus button"><i class="google plus icon"></i>Google Plus</a><a class="tiny ui instagram button"><i class="instagram icon"></i>Instagram</a></div></div></div></div>';
var getDeal = '<div class="store-listing-item shadow-box"><div class="store-thumb-link"><div class="store-thumb"><a href="#DOMAIN_NAME#store/#STORENAME#"><img src="#COUPON_THUMB#" alt=""></a></div><div class="store-name"><a href="#DOMAIN_NAME#store/#STORENAME#">#STORENAME_DISPLAY#<i class="angle right icon"></i></a></div></div><div class="latest-coupon"><h3 class="coupon-title"><a href="#">#COUPON_TITLE#</a></h3><div class="coupon-des">#COUPON_DESCRIPTION1#<span class="des-more">... <a href="#">More<i class="angle down icon"></i></a></span><div class="coupon-des-full" style="display: none;">#COUPON_DESCRIPTION2#<a class="des-less" href="#">Less<i class="angle up icon"></i></a></div></div></div><div class="coupon-detail coupon-button-type"><a href="#" class="coupon-deal coupon-button" data-aff-url="#">Get Deal <i class="shop icon"></i></a><div class="exp-text">Expires #COUPON_EXPIRY_DATE#<a title="Save This Coupon" href="#" class="coupon-save"><i class="empty star icon"></i></a></div></div><!-- Coupon Modal --><div id="#COUPON_CODE_ID#" class="ui modal coupon-modal coupon-deal-modal"><div class="coupon-header clearfix"><div class="coupon-store-thumb"><img src="thumb/stores/vientohotel.png" alt=""></div><div class="coupon-title">#COUPON_TITLE#</div><span class="close"></span></div><div class="coupon-content"><p class="coupon-type-text">Deal Activated, no coupon code required!</p><div class="modal-code"><a href="#STORE_URL#" target="_blank" class="ui button btn btn_secondary deal-actived">Go To Store<i class="angle right icon"></i></a></div><div class="clearfix"><div class="user-ratting ui icon basic buttons"><div class="ui button icon-popup" data-offset="0" data-content="This worked" data-variation="inverted"><i class="smile icon"></i></div><div class="ui button icon-popup" data-offset="0" data-content="It did not work" data-variation="inverted"><i class="frown icon"></i></div><div class="save-coupon ui button icon-popup" data-offset="0" data-content="Save this coupon" data-variation="inverted"><i class="empty star icon"></i></div></div></div><div class="clearfix"><span class="user-ratting-text">Did it work?</span><span class="show-detail"><a href="#">Coupon Detail<i class="angle down icon"></i></a></span></div><div class="coupon-popup-detail"><p>#COUPON_POPUP_DETAIL#<p><p><strong>Expires</strong>: #COUPON_EXPIRY_DATE#</p><p><strong>Submitted</strong>: #COUPON_SUBMITTED#</p></div></div><div class="coupon-footer"><ul class="clearfix"><li><span><i class="wifi icon"></i> #TOTAL_COUNT# People Used, #TODAYS_COUNT# Today</span></li><li><a class="modal-share" href="#"><i class="share alternate icon"></i> Share</a></li></ul><div class="share-modal-popup ui popup"><a class="tiny ui facebook button"><i class="facebook icon"></i>Facebook</a><a class="tiny ui twitter button"><i class="twitter icon"></i>Twitter</a><a class="tiny ui google plus button"><i class="google plus icon"></i>Google Plus</a><a class="tiny ui instagram button"><i class="instagram icon"></i>Instagram</a></div></div></div></div>';
var counter = 10;

function timeSince(date) {

	var seconds = Math.floor((new Date() - date) / 1000);

	var interval = Math.floor(seconds / 31536000);

	if (interval > 1) {
		return interval + " years";
	}
	interval = Math.floor(seconds / 2592000);
	if (interval > 1) {
		return interval + " months";
	}
	interval = Math.floor(seconds / 86400);
	if (interval > 1) {
		return interval + " days";
	}
	interval = Math.floor(seconds / 3600);
	if (interval > 1) {
		return interval + " hours";
	}
	interval = Math.floor(seconds / 60);
	if (interval > 1) {
		return interval + " minutes";
	}
	return Math.floor(seconds) + " seconds";
}

/*
 * Sample JSON data for displaying deals [ { "categoryId" : 25, "storeId" : 2,
 * "title" : "Up To 50% Off December Savings Event", "description1" : "Some
 * exclusions apply. No code required. Limited time offer", "description2" :
 * "Some exclusions apply. No code required. Limited time offer",
 * "stepsToConsume" : "Some exclusions apply. No code required. Limited time
 * offer", "viewCount" : 0, "isActive" : true, "expiryDate" : "Dec 31, 2015
 * 12:00:00 AM", "createDate" : "Dec 30, 2015 10:59:21 PM", "updateDate" : "Dec
 * 30, 2015 10:59:21 PM", "createdBy" : "itsras@gmail.com", "id" : 11, "loaded" :
 * true } ]
 */
function getCouponCode(deal) {
	var code;
	if (deal.hasOwnProperty('code')) {
		code = getCode;
	} else {
		code = getDeal;
	}

	var image;
	if (deal.hasOwnProperty('dealImage')) {
		image = deal.dealImage;
	} else if (deal.hasOwnProperty('imageName')) {
		image = deal.imageName;
	} else {
		image = "infinitygroup.png";
	}

	var domainName = document.getElementById("domainName").value;

	code = code.replace(/#DOMAIN_NAME#/g, domainName);
	code = code
			.replace(/#COUPON_THUMB#/g, domainName + "thumb/stores/" + image);
	code = code.replace(/#STORENAME#/g, deal.storeName);
	code = code.replace(/#STORENAME_DISPLAY#/g, deal.storeName);
	code = code.replace(/#COUPON_TITLE#/g, deal.title);
	code = code.replace(/#COUPON_DESCRIPTION1#/g, deal.description1);
	code = code.replace(/#COUPON_DESCRIPTION2#/g, deal.description1);
	code = code.replace(/#COUPON_CODE#/g, deal.code);
	code = code.replace(/#COUPON_POPUP_DETAIL#/g, deal.stepsToConsume);
	code = code.replace(/#STORE_URL#/g, deal.storeUrl);
	code = code.replace(/#COUPON_EXPIRY_DATE#/g, " in "
			+ timeSince(new Date(deal.expiryDate)));
	code = code.replace(/#COUPON_SUBMITTED#/g, deal.createDate);
	code = code.replace(/#TODAYS_COUNT#/g, deal.viewCount);
	code = code.replace(/#TOTAL_COUNT#/g, deal.viewCount);
	code = code.replace(/#COUPON_CODE_ID#/g, deal.id);
	return code;
}

function fetchDeals(curl) {
	$.ajax({
		url : curl,
		type : "GET",
		success : lastAddedLiveFunc,
		error : function(result) {
			$(".store-listings").append("No deals available!")
		}
	});
}

function lastAddedLiveFunc(data) {
	if (data.indexOf('[') != 0) {
		$(".store-listings").append(data)
	}

	var obj = JSON.parse(data);
	for ( var i = 0; i < obj.length; i++) {
		$(".store-listings").append(getCouponCode(obj[i]));
	}

	// Show more content
	(function() {
		$('.coupon-des-full').hide();
		$('.des-more').click(function() {
			// $('.coupon-des-full').toggleClass("des-show");
			$(this).parent().find('.coupon-des-full').show();
			$(this).hide();
			return false;
		});
		$('.des-less').click(function() {
			$('.coupon-des-full').hide();
			$('.des-more').show();
			return false;
		});
	})();

	// Coupon Filter
	(function() {
		$('.coupon-filter a').click(function() {
			$('.coupon-filter a').removeClass('active');
			$(this).addClass('active');
		});
	})();

	// Listing Item
	(function() {
		var store_listing_item = $('.store-listing-item');
		store_listing_item.each(function() {
			// Open Modal
			var coupon_modal = $(this).find('.coupon-modal');
			var coupon_id = coupon_modal.attr('id');
			$(this).find('.coupon-button-type .coupon-button').click(
					function() {
						var aff_url = $(this).attr('data-aff-url');
						var current_url = $(location).attr('href');
						// window.open(aff_url, '_self');
						// window.open('#'+ coupon_id,'_blank');
						$(coupon_modal).modal('show');
						return false;
					});
			var coupon_hash = $(location).attr('hash');
			if (coupon_hash == '#' + coupon_id) {
				$(coupon_modal).modal('show');
			}

			// Reveal box
			var reveal_link = $(this).find('.coupon-footer li a');
			var reveal_content = $(this).find('.reveal-content');
			reveal_link.each(function() {
				$(this).click(function() {

					reveal_link.removeClass('active');
					$(this).addClass('active');
					// $(this).toggleClass('active');

					var reveal_link_data = $(this).attr('data-reveal');
					reveal_content.each(function() {
						if ($(this).hasClass(reveal_link_data)) {
							reveal_content.removeClass('active');
							$(this).addClass('active');

							$(this).find('.close').click(function() {
								$(this).parent().removeClass('active');
								reveal_link.removeClass('active');
							});
						}
					});
					return false;
				});
			});
		});
	})();

	// Initializing Popup on hover
	(function() {

		$('.icon-popup, .coupon-save').popup({
			inline : true,
			variation : 'inverted',
			position : 'bottom left',
			offset : 0
		});

		// Share popup on modal
		$('.modal-share').popup({
			popup : $('.share-modal-popup'),
			on : 'click',
			hoverable : true,
			position : 'top right'
		});

	})();

	// Show coupon detail on modal
	(function() {
		$('.coupon-modal').each(function() {
			var coupon_popup_detail = $(this).find('.coupon-popup-detail');
			var show_detail = $(this).find('.show-detail a');
			coupon_popup_detail.hide();

			$(show_detail).click(function() {
				if ($(show_detail).hasClass('show-detail-on')) {
					coupon_popup_detail.hide();
					$(this).removeClass('show-detail-on');
					$(this).find('i').removeClass('up').addClass('down');
				} else {
					coupon_popup_detail.show();
					$(this).addClass('show-detail-on');
					$(this).find('i').removeClass('down').addClass('up');
				}
				return false;
			});

		});
	})();

	// Add to favorite
	$('.add-favorite').click(function() {
		$('.stuser-login-btn').eq(0).click();
		return false;
	});

	// Save Coupon
	$('.save-coupon, .coupon-save').click(function() {
		$('.stuser-login-btn').eq(0).click();
		return false;
	});
};

$(document)
		.ready(
				function() {
					var id = $('.btn_primary').attr("id");
					if (id == "loadCoupons") {
						fetchDeals("/?ajax=true&page=Home");
					}
					if (id == "loadStoreCoupons") {
						var name = document.getElementById("stname").value;
						fetchDeals("/?ajax=true&page=store&name=" + name);
					}
					if (id == "loadCatCoupons") {
						var name = document.getElementById("catname").value;
						var subcatname = document.getElementById("subcatname").value;

						fetchDeals("/?ajax=true&page=category&name=" + name
								+ "&subcat=" + subcatname);
					}

					$('.btn_primary')
							.click(
									function(event) {
										if ($(this).attr("id") == "loadCoupons") {
											fetchDeals("/?ajax=true&page=Home");
										}
										if ($(this).attr("id") == "loadStoreCoupons") {
											var name = document
													.getElementById("stname").value;
											fetchDeals("/?ajax=true&page=store&name="
													+ name);
										}
										if ($(this).attr("id") == "loadCatCoupons") {
											var name = document
													.getElementById("catname").value;
											var subcatname = document
													.getElementById("subcatname").value;

											fetchDeals("/?ajax=true&page=category&name="
													+ name
													+ "&subcat="
													+ subcatname);
										}
									});

					/*
					 * $(window).scroll(function(){ var wintop =
					 * $(window).scrollTop(), docheight = $(document).height(),
					 * winheight = $(window).height(); var scrolltrigger = 0.75;
					 * 
					 * if ((wintop/(docheight-winheight)) > scrolltrigger) {
					 * //console.log('scroll bottom'); fetchDeals(); } });
					 */
				});