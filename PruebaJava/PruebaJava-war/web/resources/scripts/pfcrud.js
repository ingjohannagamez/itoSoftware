function handleSubmit(xhr, status, args, dialog) {
    var jqDialog = jQuery('#' + dialog.id);
    if (args.validationFailed) {
        jqDialog.effect('shake', {times: 3}, 100);
    } else {
        dialog.hide();
    }
}
function fixPFMDialogs() {
    jQuery("body > div[data-role='page']").each(function (i) {
        var pageId = jQuery(this).attr("id");
        jQuery("body > div[id*='" + pageId + "'][class*='ui-popup']").appendTo("#" + pageId);
    });
}
function handleLoginRequest(xhr, status, args) {
    if (args.validationFailed || !args.loggedIn) {
        PF('dlg').jq.effect("shake", {times: 5}, 100);
    } else {
        PF('dlg').hide();
        $('#loginLink').fadeOut();
    }
}
