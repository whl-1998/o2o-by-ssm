function changeVerifyCode(img) {
    img.src = "../Kaptcha?" + Math.floor(Math.random()*100);
}

/**
 * 从url中获取对应值
 * @param name
 * @returns {string}
 */
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return '';
}