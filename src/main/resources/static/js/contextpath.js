function getContextPath() {
    var pathName = document.location.pathname;
    alert(pathName)
    var index = pathName.substr(1).indexOf("/");
    var result = pathName.substr(0,index+1);
    return result;
}
/*
function getContextPaths() {
    return [[${#httpServletRequest.getContextPath()}]];

}*/