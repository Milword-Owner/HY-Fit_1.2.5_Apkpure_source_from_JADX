package p005cn.sharesdk.framework.utils;

import java.io.IOException;

/* renamed from: cn.sharesdk.framework.utils.h */
/* compiled from: UnicodeEscaper */
public abstract class C0810h implements Escaper {
    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract char[] mo10928a(int i);

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public int mo10927a(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            int b = m691b(charSequence, i, i2);
            if (b < 0 || mo10928a(b) != null) {
                break;
            }
            i += Character.isSupplementaryCodePoint(b) ? 2 : 1;
        }
        return i;
    }

    public String escape(String str) {
        int length = str.length();
        int a = mo10927a((CharSequence) str, 0, length);
        return a == length ? str : mo10936a(str, a);
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public final String mo10936a(String str, int i) {
        int i2;
        int length = str.length();
        char[] cArr = (char[]) new C0812a().get();
        int i3 = 0;
        int i4 = 0;
        while (i < length) {
            int b = m691b(str, i, length);
            if (b >= 0) {
                char[] a = mo10928a(b);
                if (a != null) {
                    int i5 = i - i3;
                    int i6 = i4 + i5;
                    int length2 = a.length + i6;
                    if (cArr.length < length2) {
                        cArr = m690a(cArr, i4, length2 + (length - i) + 32);
                    }
                    if (i5 > 0) {
                        str.getChars(i3, i, cArr, i4);
                        i4 = i6;
                    }
                    if (a.length > 0) {
                        System.arraycopy(a, 0, cArr, i4, a.length);
                        i4 += a.length;
                    }
                }
                i3 = (Character.isSupplementaryCodePoint(b) ? 2 : 1) + i;
                i = mo10927a((CharSequence) str, i3, length);
            } else {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
        }
        int i7 = length - i3;
        if (i7 > 0) {
            i2 = i7 + i4;
            if (cArr.length < i2) {
                cArr = m690a(cArr, i4, i2);
            }
            str.getChars(i3, length, cArr, i4);
        } else {
            i2 = i4;
        }
        return new String(cArr, 0, i2);
    }

    public Appendable escape(final Appendable appendable) {
        C0806d.m676a(appendable);
        return new Appendable() {

            /* renamed from: a */
            int f620a = -1;

            /* renamed from: b */
            char[] f621b = new char[2];

            public Appendable append(CharSequence charSequence) throws IOException {
                return append(charSequence, 0, charSequence.length());
            }

            public Appendable append(CharSequence charSequence, int i, int i2) throws IOException {
                int i3;
                if (i < i2) {
                    if (this.f620a != -1) {
                        char charAt = charSequence.charAt(i);
                        int i4 = i + 1;
                        if (Character.isLowSurrogate(charAt)) {
                            char[] a = C0810h.this.mo10928a(Character.toCodePoint((char) this.f620a, charAt));
                            if (a != null) {
                                m695a(a, a.length);
                                i = i4;
                            } else {
                                appendable.append((char) this.f620a);
                            }
                            this.f620a = -1;
                            i3 = i;
                            i = i4;
                        } else {
                            throw new IllegalArgumentException("Expected low surrogate character but got " + charAt);
                        }
                    } else {
                        i3 = i;
                    }
                    while (true) {
                        int a2 = C0810h.this.mo10927a(charSequence, i, i2);
                        if (a2 > i3) {
                            appendable.append(charSequence, i3, a2);
                        }
                        if (a2 == i2) {
                            break;
                        }
                        int b = C0810h.m691b(charSequence, a2, i2);
                        if (b < 0) {
                            this.f620a = -b;
                            break;
                        }
                        char[] a3 = C0810h.this.mo10928a(b);
                        if (a3 != null) {
                            m695a(a3, a3.length);
                        } else {
                            m695a(this.f621b, Character.toChars(b, this.f621b, 0));
                        }
                        i3 = (Character.isSupplementaryCodePoint(b) ? 2 : 1) + a2;
                        i = i3;
                    }
                }
                return this;
            }

            public Appendable append(char c) throws IOException {
                if (this.f620a != -1) {
                    if (Character.isLowSurrogate(c)) {
                        char[] a = C0810h.this.mo10928a(Character.toCodePoint((char) this.f620a, c));
                        if (a != null) {
                            m695a(a, a.length);
                        } else {
                            appendable.append((char) this.f620a);
                            appendable.append(c);
                        }
                        this.f620a = -1;
                    } else {
                        throw new IllegalArgumentException("Expected low surrogate character but got '" + c + "' with value " + c);
                    }
                } else if (Character.isHighSurrogate(c)) {
                    this.f620a = c;
                } else if (!Character.isLowSurrogate(c)) {
                    char[] a2 = C0810h.this.mo10928a(c);
                    if (a2 != null) {
                        m695a(a2, a2.length);
                    } else {
                        appendable.append(c);
                    }
                } else {
                    throw new IllegalArgumentException("Unexpected low surrogate character '" + c + "' with value " + c);
                }
                return this;
            }

            /* renamed from: a */
            private void m695a(char[] cArr, int i) throws IOException {
                for (int i2 = 0; i2 < i; i2++) {
                    appendable.append(cArr[i2]);
                }
            }
        };
    }

    /* renamed from: b */
    protected static final int m691b(CharSequence charSequence, int i, int i2) {
        if (i < i2) {
            char charAt = charSequence.charAt(i);
            int i3 = i + 1;
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            if (charAt > 56319) {
                StringBuilder sb = new StringBuilder();
                sb.append("Unexpected low surrogate character '");
                sb.append(charAt);
                sb.append("' with value ");
                sb.append(charAt);
                sb.append(" at index ");
                sb.append(i3 - 1);
                throw new IllegalArgumentException(sb.toString());
            } else if (i3 == i2) {
                return -charAt;
            } else {
                char charAt2 = charSequence.charAt(i3);
                if (Character.isLowSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
                throw new IllegalArgumentException("Expected low surrogate but got char '" + charAt2 + "' with value " + charAt2 + " at index " + i3);
            }
        } else {
            throw new IndexOutOfBoundsException("Index exceeds specified range");
        }
    }

    /* renamed from: a */
    private static final char[] m690a(char[] cArr, int i, int i2) {
        char[] cArr2 = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, cArr2, 0, i);
        }
        return cArr2;
    }

    /* renamed from: cn.sharesdk.framework.utils.h$a */
    /* compiled from: UnicodeEscaper */
    private static final class C0812a extends ThreadLocal<char[]> {
        private C0812a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public char[] initialValue() {
            return new char[1024];
        }
    }
}
