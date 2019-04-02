var getFormItem = function (form, formItemName) {
    var itemObj;
    var itemObj = $("#" + form + " input[name='" + formItemName + "']");
    if (itemObj.length == 0) {
        itemObj = $("#" + form + " select[name='" + formItemName + "']");
    }
    if (itemObj.length == 0) {
        itemObj = $("#" + form + " textarea[name='" + formItemName + "']");
    }

    if (!itemObj.attr('class') && $(itemObj).prev().attr('class').indexOf("easyui-numberbox") >= 0) {
        itemObj = $(itemObj).prev();
    }
    console.info(1);
    if(itemObj.attr('class').indexOf("combo-value") >= 0 && $(itemObj).parent().prev().attr('class').indexOf("easyui-combobox") >= 0){
        itemObj = $(itemObj).parent().prev();
        console.info(2,itemObj);
    }
    return itemObj;
}

var getFromItemValue = function (form, formItemName) {
    var itemObj = getFormItem(form, formItemName);
    var itemClass = itemObj.attr('class');
    var itemValue;
    if (itemClass.indexOf("easyui-combobox") >= 0) {
        itemValue = $(itemObj).combobox("getValue");
    } else if (itemClass.indexOf("easyui-numberbox") >= 0) {
        itemValue = $(itemObj).numberbox('getValue');
    } else {
        itemValue = itemObj.val();
    }
    return itemValue;
}

var setFormItemValue = function (form, formItemName, formItemValue) {
    var itemObj = getFormItem(form, formItemName);
    var itemClass = itemObj.attr('class');
    var itemValue;
    if (itemClass.indexOf("easyui-combobox") >= 0) {
        itemValue = $(itemObj).combobox("select", formItemValue);
    } else if (itemClass.indexOf("easyui-numberbox") >= 0) {
        itemValue = $(itemObj).numberbox('setValue', formItemValue);
    } else {
        itemValue = itemObj.val(formItemValue);
    }
}

var setFormItemDefaultValue = function (form, formItemName, formItemValue) {
    var itemValue = getFromItemValue(form, formItemName);
    console.info("itemValue",itemValue);
    if (!itemValue && itemValue == '') {
        setFormItemValue(form, formItemName, formItemValue);
    }
}