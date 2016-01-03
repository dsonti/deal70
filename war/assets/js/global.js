jQuery(document).ready(function() {
	"use strict";

	var $ = jQuery,
	html = $('html');

	// IE<8 Warning
	if (html.hasClass("ie6") || html.hasClass("ie7")) {
		$("body").empty().html('UPDATE YOUR BROWSER');
	}

	// Superfish Menu Toggle
	( function() {
		$('#nav-toggle').click(
			function () {
				$('.primary-navigation .wpc-menu').toggleClass("wpc-menu-mobile");
			}
		);
		$('.nav-toggle-subarrow, .nav-toggle-subarrow .nav-toggle-subarrow').click(
			function () {
				$(this).parent().toggleClass("nav-toggle-dropdown");
			}
		);
	} )();

	// Show more content
	( function() {
		$('.coupon-des-full').hide();
		$('.des-more').click( function () {
			//$('.coupon-des-full').toggleClass("des-show");
			$(this).parent().find('.coupon-des-full').show();
			$(this).hide();
			return false;
		});
		$('.des-less').click( function() {
			$('.coupon-des-full').hide();
			$('.des-more').show();
			return false;
		});
	} )();

	// Coupon Filter
	( function() {
		$('.coupon-filter a').click( function() {
			$('.coupon-filter a').removeClass('active');
			$(this).addClass('active');
		});
	} )();

	// Listing Item
	( function() {
		var store_listing_item = $('.store-listing-item');
		store_listing_item.each( function(){
			// Open Modal
			var coupon_modal = $(this).find('.coupon-modal');
			var coupon_id = coupon_modal.attr('id');
			$(this).find('.coupon-button-type .coupon-button').click( function () {
				var aff_url = $(this).attr('data-aff-url');
				var current_url = $(location).attr('href');
				window.open(aff_url, '_self');
				window.open('#'+ coupon_id,'_blank');
				$(coupon_modal).modal('show');
				return false;
			} );
			var coupon_hash = $(location).attr('hash');
			if ( coupon_hash == '#'+coupon_id ) {
				$(coupon_modal).modal('show');
			}

			// Reveal box
			var reveal_link = $(this).find('.coupon-footer li a');
			var reveal_content = $(this).find('.reveal-content');
			reveal_link.each( function() {
				$(this).click( function(){

					reveal_link.removeClass('active');
					$(this).addClass('active');
					//$(this).toggleClass('active');

					var reveal_link_data = $(this).attr('data-reveal');
					reveal_content.each( function() {
						if( $(this).hasClass(reveal_link_data) ) {
							reveal_content.removeClass('active');
							$(this).addClass('active');
							
							$(this).find('.close').click( function() {
								$(this).parent().removeClass('active');
								reveal_link.removeClass('active');
							});
						}
					});
					return false;
				});
			} );
		});
	} )();

	// Initializing Popup on hover
	( function() {

		$('.icon-popup, .coupon-save').popup({
            inline: true,
            variation: 'inverted',
            position: 'bottom left',
            offset: 0
        });

		// Share popup on modal
		$('.modal-share').popup({
			popup : $('.share-modal-popup'),
			on    : 'click',
			hoverable: true,
			position : 'top right'
		});

	} )();

	// Initializing Form Elements
	( function() {
		$('.dropdown').dropdown();
		$('.ui.checkbox').checkbox();
	} )();

	// Initializing Search Loading
    ( function() {
        // submit search form when click button
        $( 'form#header-search .button').click( function(){
        	searchStores();
        });

        $('#searchStr').keyup(function() {
        	searchStores();
        });
    } )();


	
	// Show coupon detail on modal
	( function() {
		$('.coupon-modal').each( function() {
			var coupon_popup_detail = $(this).find('.coupon-popup-detail');
			var show_detail = $(this).find('.show-detail a');
			coupon_popup_detail.hide();
			
			$(show_detail).click( function() {
				if ( $(show_detail).hasClass('show-detail-on') ) {
					coupon_popup_detail.hide();
					$(this).removeClass('show-detail-on');
					$(this).find('i').removeClass('up').addClass('down');
				} else {
					coupon_popup_detail.show();
					$(this).addClass('show-detail-on');
					$(this).find('i').removeClass('down').addClass('up');
				}
				return false;
			} );

		} );
	} )();


    // Add to favorite
    $('.add-favorite').click( function() {
        $('.stuser-login-btn').eq(0).click();
        return false;
    });

    // Save Coupon
    $( '.save-coupon, .coupon-save').click( function(){
        $( '.stuser-login-btn').eq( 0).click();
        return false;
    } );

});

function searchStores()
{
	var r;
	$.ajax({
		url : $('#domainName').val() + "?ajax=true&search="+$('#searchStr').val(),
		type : "POST",
		success : function(result) {
			var obj = JSON.parse(result);
			//[{"name":"Amazon","imageName":"amazon.png","url":"http://www.amazon.in","isPopular":true,"isOnline":true,"viewCount":0,
			//"createDate":"Dec 30, 2015 10:42:13 PM","updateDate":"Dec 30, 2015 10:42:13 PM","createdBy":"itsras@gmail.com","id":2,
			//"loaded":true}]  

            if (  $('.ui.search').find( '.results').length > 0 ) {

            } else {
            	 $('.ui.search').append( '<div class="results"></div>' );
            }

            r =   $('.ui.search').find( '.results');
            var html = '';
            if ( obj.length > 0  ) {
				for(var i =0;i < obj.length;i++)
				{
						html+=  '<div class="result">'+
                        '<a href="' + $('#domainName').val() +'store/'+obj[i].name +'"></a>' +
                        '<div class="image">'+
                        '<img src="'+$('#domainName').val()+"thumb/stores/"+obj[i].imageName+'"></a></div>'+
                        '<div class="content">' +
                        '<div class="title">'+obj[i].name+'</div>' +
                        '<div class="description"></div>' +
                        '</div>'+
                        '</div>';
				}
            }
            if ( html !== '' ) {
                var w =  $('.ui.search').find( '.prompt').outerWidth();
                html+='<a class="action" href="'+$('#domainName').val() +'stores'+'">'+'Show All Stores'+'</a>';
                r.html(html);
                r.css( { 'width': w+'px' } ).addClass('items ui transition visible');
            }
            else {
            	r.html(html);
            	r.removeClass('items ui transition visible');
            }
		},
		error : function(result) {
			r.removeClass('items ui transition visible');
		}
	});	
}

jQuery(document).mouseup(function (e){
	var $ = jQuery;
    var container = $(".reveal-content");
    if (!container.is(e.target) && container.has(e.target).length === 0){
        container.removeClass('active');
        $('.coupon-footer li a').removeClass('active');
    }
});