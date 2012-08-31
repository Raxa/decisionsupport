
I've made several modifications for our use of dwr.  The first was a result of dwr removing
functionality that we were depending on.  In the second, I'm adding attributes to facilitate 
modules.  I made the change to the doctype just so errors would disappear.

Modified \java\uk\ltd\getahead\dwr\util.js :
 - Replaced 779:786 with:
	if (DWRUtil._isHTMLElement(reply, "td")) {
		tr.appendChild(reply);
	}
	else {
		var td = options.cellCreator(options);
		if (td != null) {
			if (reply != null) {
				if (DWRUtil._isHTMLElement(reply)) td.appendChild(reply);
				else td.innerHTML = reply;
			}
			tr.appendChild(td);
		}
	}

Modified \java\uk\ltd\getahead\dwr\dwr10.dtd :
 - Added 
    <!ATTLIST signatures
     moduleId CDATA #IMPLIED
    >
    
 - Added
    <!ATTLIST allow
     moduleId CDATA #IMPLIED
    >