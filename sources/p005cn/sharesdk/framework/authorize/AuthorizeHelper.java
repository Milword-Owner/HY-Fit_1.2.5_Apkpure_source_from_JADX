package p005cn.sharesdk.framework.authorize;

import p005cn.sharesdk.framework.Platform;

/* renamed from: cn.sharesdk.framework.authorize.AuthorizeHelper */
public interface AuthorizeHelper {
    AuthorizeListener getAuthorizeListener();

    String getAuthorizeUrl();

    C0698c getAuthorizeWebviewClient(C0702g gVar);

    Platform getPlatform();

    String getRedirectUri();

    SSOListener getSSOListener();

    C0700e getSSOProcessor(C0699d dVar);
}
