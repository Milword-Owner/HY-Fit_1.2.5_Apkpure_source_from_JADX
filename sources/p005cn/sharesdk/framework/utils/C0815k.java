package p005cn.sharesdk.framework.utils;

import android.text.TextUtils;
import android.util.Xml;
import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* renamed from: cn.sharesdk.framework.utils.k */
/* compiled from: XmlParser */
public class C0815k {
    /* renamed from: a */
    public HashMap<String, Object> mo10942a(String str) throws Throwable {
        C0816a aVar = new C0816a();
        Xml.parse(str, aVar);
        return aVar.mo10943a();
    }

    /* renamed from: cn.sharesdk.framework.utils.k$a */
    /* compiled from: XmlParser */
    private static class C0816a extends DefaultHandler {

        /* renamed from: a */
        private HashMap<String, Object> f625a = new HashMap<>();

        /* renamed from: b */
        private HashMap<String, Object> f626b;

        /* renamed from: a */
        public HashMap<String, Object> mo10943a() {
            return this.f625a;
        }

        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (this.f626b != null) {
                HashMap<String, Object> hashMap = new HashMap<>();
                this.f626b.put(str2, hashMap);
                this.f626b = hashMap;
            } else {
                this.f626b = new HashMap<>();
                this.f625a.put(str2, this.f626b);
            }
            int length = attributes.getLength();
            for (int i = 0; i < length; i++) {
                this.f626b.put(attributes.getLocalName(i), attributes.getValue(i));
            }
        }

        public void endElement(String str, String str2, String str3) throws SAXException {
            this.f626b = null;
        }

        public void characters(char[] cArr, int i, int i2) {
            HashMap<String, Object> hashMap;
            String trim = String.valueOf(cArr, i, i2).trim();
            if (!TextUtils.isEmpty(trim) && (hashMap = this.f626b) != null) {
                hashMap.put("value", trim);
            }
        }
    }
}
