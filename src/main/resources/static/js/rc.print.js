//(function ($) {
//    var counter = 0;
//    var modes = { iframe: "iframe", popup: "popup" };
//    var defaults = {
//        mode: modes.iframe,
//        popHt: 500,
//        popWd: 400,
//        popX: 200,
//        popY: 200,
//        popTitle: '',
//        popClose: false
//    };

//    var settings = {}; //global settings

//    $.fn.printArea = function (options) {
//        $.extend(settings, defaults, options);

//        counter++;
//        var idPrefix = "printArea_";
//        $("[id^=" + idPrefix + "]").remove();
//        var ele = getFormData($(this));

//        settings.id = idPrefix + counter;

//        var writeDoc;
//        var printWindow;

//        switch (settings.mode) {
//            case modes.iframe:
//                var f = new Iframe();
//                writeDoc = f.doc;
//                printWindow = f.contentWindow || f;
//                break;
//            case modes.popup:
//                printWindow = new Popup();
//                writeDoc = printWindow.doc;
//        }

//        writeDoc.open();
//        writeDoc.write(docType() + "<html>" + getHead() + getBody(ele) + "</html>");
//        writeDoc.close();

//        printWindow.focus();
//        printWindow.print();

//        if (settings.mode == modes.popup && settings.popClose)
//            printWindow.close();
//    }

//    function docType() {
//        if (settings.mode == modes.iframe || !settings.strict) return "";

//        var standard = settings.strict == false ? " Trasitional" : "";
//        var dtd = settings.strict == false ? "loose" : "strict";

//        return '<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01' + standard + '//EN" "http://www.w3.org/TR/html4/' + dtd + '.dtd">';
//    }

//    function getHead() {
//        var head = "<head><title>" + settings.popTitle + "</title>";
//        $(document).find("link")
//            .filter(function () {
//                return $(this).attr("rel").toLowerCase() == "stylesheet";
//            })
//            .filter(function () { // this filter contributed by "mindinquiring"
//                var media = $(this).attr("media");
//                if (media == undefined) {
//                    return false;
//                }
//                else {
//                    return (media.toLowerCase() == "" || media.toLowerCase() == "print");
//                }
//            })
//            .each(function () {
//                head += '<link type="text/css" rel="stylesheet" href="' + $(this).attr("href") + '" >';
//            });
//        head += "</head>";
//        return head;
//    }

//    function getBody(printElement) {
//        return '<body><div class="' + $(printElement).attr("class") + '">' + $(printElement).html() + '</div></body>';
//    }

//    function getFormData(ele) {
//        $("input,select,textarea", ele).each(function () {
//            // In cases where radio, checkboxes and select elements are selected and deselected, and the print
//            // button is pressed between select/deselect, the print screen shows incorrectly selected elements.
//            // To ensure that the correct inputs are selected, when eventually printed, we must inspect each dom element
//            var type = $(this).attr("type");
//            if (type == "radio" || type == "checkbox") {
//                if ($(this).is(":not(:checked)")) this.removeAttribute("checked");
//                else this.setAttribute("checked", true);
//            }
//            else if (type == "text")
//                this.setAttribute("value", $(this).val());
//            else if (type == "select-multiple" || type == "select-one")
//                $(this).find("option").each(function () {
//                    if ($(this).is(":not(:selected)")) this.removeAttribute("selected");
//                    else this.setAttribute("selected", true);
//                });
//            else if (type == "textarea") {
//                var v = $(this).attr("value");
//                if ($.browser.mozilla) {
//                    if (this.firstChild) this.firstChild.textContent = v;
//                    else this.textContent = v;
//                }
//                else this.innerHTML = v;
//            }
//        });
//        return ele;
//    }

//    function Iframe() {
//        var frameId = settings.id;
//        var iframeStyle = 'border:0;position:absolute;width:0px;height:0px;left:0px;top:0px;';
//        var iframe;

//        try {
//            iframe = document.createElement('iframe');
//            document.body.appendChild(iframe);
//            $(iframe).attr({ style: iframeStyle, id: frameId, src: "" });
//            iframe.doc = null;
//            iframe.doc = iframe.contentDocument ? iframe.contentDocument : (iframe.contentWindow ? iframe.contentWindow.document : iframe.document);
//        }
//        catch (e) { throw e + ". iframes may not be supported in this browser."; }

//        if (iframe.doc == null) throw "Cannot find document.";

//        return iframe;
//    }

//    function Popup() {
//        var windowAttr = "location=no,statusbar=no,directories=no,menubar=no,titlebar=no,toolbar=no,dependent=no";
//        windowAttr += ",width=595px,height=842px,top=0,left=0,toolbar=no,scrollbars=no,personalbar=no";
//        windowAttr += ",resizable=yes,screenX=" + settings.popX + ",screenY=" + settings.popY + "";

//        var newWin = window.open("", "_blank", windowAttr);

//        newWin.doc = newWin.document;

//        return newWin;
//    }

//})(jQuery);

(function ($) {
    var counter = 0;
    var modes = { iframe: "iframe", popup: "popup" };
    var standards = { strict: "strict", loose: "loose", html5: "html5" };
    var defaults = {
        mode: modes.iframe,
        standard: standards.html5,
        popHt: 500,
        popWd: 400,
        popX: 200,
        popY: 200,
        popTitle: '',
        popClose: false,
        extraCss: '',
        extraHead: '',
        retainAttr: ["id", "class", "style"]
    };

    var settings = {};//global settings

    $.fn.printArea = function (options) {
        $.extend(settings, defaults, options);

        counter++;
        var idPrefix = "printArea_";
        $("[id^=" + idPrefix + "]").remove();

        settings.id = idPrefix + counter;

        var $printSource = $(this);

        var PrintAreaWindow = PrintArea.getPrintWindow();

        PrintArea.write(PrintAreaWindow.doc, $printSource);

        setTimeout(function () { PrintArea.print(PrintAreaWindow); }, 1000);
    };

    var PrintArea = {
        print: function (PAWindow) {
            var paWindow = PAWindow.win;

            $(PAWindow.doc).ready(function () {
                paWindow.focus();
                paWindow.print();

                if (settings.mode == modes.popup && settings.popClose)
                    setTimeout(function () { paWindow.close(); }, 2000);
            });
        },
        write: function (PADocument, $ele) {
            PADocument.open();
            PADocument.write(PrintArea.docType() + "<html>" + PrintArea.getHead() + PrintArea.getBody($ele) + "</html>");
            PADocument.close();
        },
        docType: function () {
            if (settings.mode == modes.iframe) return "";

            if (settings.standard == standards.html5) return "<!DOCTYPE html>";

            var transitional = settings.standard == standards.loose ? " Transitional" : "";
            var dtd = settings.standard == standards.loose ? "loose" : "strict";

            return '<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01' + transitional + '//EN" "http://www.w3.org/TR/html4/' + dtd + '.dtd">';
        },
        getHead: function () {
            var extraHead = "";
            var links = "";

            if (settings.extraHead) settings.extraHead.replace(/([^,]+)/g, function (m) { extraHead += m });

            $(document).find("link")
                .filter(function () { // Requirement: <link> element MUST have rel="stylesheet" to be considered in print document
                    var relAttr = $(this).attr("rel");
                    return ($.type(relAttr) === 'undefined') == false && relAttr.toLowerCase() == 'stylesheet';
                })
                .filter(function () { // Include if media is undefined, empty, print or all
                    var mediaAttr = $(this).attr("media");
                    return $.type(mediaAttr) === 'undefined' || mediaAttr == "" || mediaAttr.toLowerCase() == 'print' || mediaAttr.toLowerCase() == 'all'
                })
                .each(function () {
                    links += '<link type="text/css" rel="stylesheet" href="' + $(this).attr("href") + '" >';
                });
            if (settings.extraCss) settings.extraCss.replace(/([^,\s]+)/g, function (m) { links += '<link type="text/css" rel="stylesheet" href="' + m + '">' });

            return "<head><title>" + settings.popTitle + "</title>" + extraHead + links + "</head>";
        },
        getBody: function (elements) {
            var htm = "";
            var attrs = settings.retainAttr;
            elements.each(function () {
                var ele = PrintArea.getFormData($(this));

                var attributes = ""
                for (var x = 0; x < attrs.length; x++) {
                    var eleAttr = $(ele).attr(attrs[x]);
                    if (eleAttr) attributes += (attributes.length > 0 ? " " : "") + attrs[x] + "='" + eleAttr + "'";
                }

                htm += '<div ' + attributes + '>' + $(ele).html() + '</div>';
            });

            return "<body>" + htm + "</body>";
        },
        getFormData: function (ele) {
            var copy = ele.clone();
            var copiedInputs = $("input,select,textarea", copy);
            $("input,select,textarea", ele).each(function (i) {
                var typeInput = $(this).attr("type");
                if ($.type(typeInput) === 'undefined') typeInput = $(this).is("select") ? "select" : $(this).is("textarea") ? "textarea" : "";
                var copiedInput = copiedInputs.eq(i);

                if (typeInput == "radio" || typeInput == "checkbox") copiedInput.attr("checked", $(this).is(":checked"));
                else if (typeInput == "text") copiedInput.attr("value", $(this).val());
                else if (typeInput == "select")
                    $(this).find("option").each(function (i) {
                        if ($(this).is(":selected")) $("option", copiedInput).eq(i).attr("selected", true);
                    });
                else if (typeInput == "textarea") copiedInput.text($(this).val());
            });
            return copy;
        },
        getPrintWindow: function () {
            switch (settings.mode) {
                case modes.iframe:
                    var f = new PrintArea.Iframe();
                    return { win: f.contentWindow || f, doc: f.doc };
                case modes.popup:
                    var p = new PrintArea.Popup();
                    return { win: p, doc: p.doc };
            }
        },
        Iframe: function () {
            var frameId = settings.id;
            var iframeStyle = 'border:0;position:absolute;width:0px;height:0px;right:0px;top:0px;';
            var iframe;

            try {
                iframe = document.createElement('iframe');
                document.body.appendChild(iframe);
                $(iframe).attr({ style: iframeStyle, id: frameId, src: "#" + new Date().getTime() });
                iframe.doc = null;
                iframe.doc = iframe.contentDocument ? iframe.contentDocument : (iframe.contentWindow ? iframe.contentWindow.document : iframe.document);
            }
            catch (e) { throw e + ". iframes may not be supported in this browser."; }

            if (iframe.doc == null) throw "Cannot find document.";

            return iframe;
        },
        Popup: function () {
            var windowAttr = "location=yes,statusbar=no,directories=no,menubar=no,titlebar=no,toolbar=no,dependent=no";
            windowAttr += ",width=" + settings.popWd + ",height=" + settings.popHt;
            windowAttr += ",resizable=yes,screenX=" + settings.popX + ",screenY=" + settings.popY + ",personalbar=no,scrollbars=yes";

            var newWin = window.open("", "_blank", windowAttr);

            newWin.doc = newWin.document;

            return newWin;
        }
    };
})(jQuery);