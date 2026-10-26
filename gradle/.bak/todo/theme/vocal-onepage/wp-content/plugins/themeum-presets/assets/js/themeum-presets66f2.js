jQuery(function($) {
  var themeUrl    = $('.style-chooser').data('stylesheeturl');
  var stylesheet  = $('.style-chooser').data('stylesheet');
  var presets     = $('.style-chooser ul li');

  //Get cookie
  var activePreset = $.cookie(stylesheet);
  if(activePreset!=undefined) {
    $('#themeum-preset-css').removeAttr('href').attr('href', themeUrl + '/css/presets/preset' + activePreset + '.css');
    presets.removeClass('active');
    $(presets[activePreset-1]).addClass('active');
  }

  //Toggle
  $('.style-chooser .toggler').on('click', function(event){
    event.preventDefault();
    $(this).closest('.style-chooser').toggleClass('opened');
  });

  //Change Preset
  $('.style-chooser ul li a').on('click', function(event){
    event.preventDefault();
    var currentPreset = $(this).parent().data('preset');
    $.cookie(stylesheet, currentPreset, { expires : 1 });

    presets.removeClass('active');
    $(this).parent().addClass('active');
    $('#themeum-preset-css').removeAttr('href').attr('href', themeUrl + '/css/presets/preset' + currentPreset + '.css');
  })
});