//package xyz.liut.logcat.android;
//
//
//
///**
// * Create by liut on 2018/10/15 0015
// */
//public class AndroidLogcatHandler implements LogHandler {
//
//    private static final String BASE_TAG = "KindleLaw_1.0_";
//
//    private static final boolean NEED_TAG = "release".equalsIgnoreCase(BuildConfig.BUILD_TYPE);
//
//    @Override
//    public void log(int level, @Nullable String tag, @Nullable String msg) {
//        if (NEED_TAG) {
//            tag = BASE_TAG + tag;
//        }
//
//        switch (level) {
//            case LogHandler.ASSERT:
//                break;
//            case LogHandler.DEBUG:
//                Log.d(tag, msg);
//                break;
//            case LogHandler.ERROR:
//                Log.e(tag, msg);
//                break;
//            case LogHandler.INFO:
//                Log.i(tag, msg);
//                break;
//            case LogHandler.VERBOSE:
//                Log.v(tag, msg);
//                break;
//            case LogHandler.WARN:
//                Log.w(tag, msg);
//                break;
//            default:
//                break;
//        }
//
//    }
//
//}
