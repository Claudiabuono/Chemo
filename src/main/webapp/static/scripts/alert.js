bootstrap_alert = function() {}
bootstrap_alert.warning = function(message) {
    $('#alert-box').html('<div class="alert alert-warning"><span>' + message + '</span></div>')
}
bootstrap_alert.danger = function(message) {
    $('#alert-box').html('<div class="alert alert-danger"><span>' + message + '</span></div>')
}

function showAlertWarning(error) {
    bootstrap_alert.warning(error);
    window.scrollTo(0, 0);
}
function showAlertDanger(error) {
    bootstrap_alert.danger(error);
    window.scrollTo(0, 0);
}

