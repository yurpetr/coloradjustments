package com.yurpetr.coloradjustments;

import com.sun.jna.platform.win32.WinUser.*;
import com.sun.jna.platform.win32.WinDef.*;
import com.sun.jna.platform.win32.BaseTSD.*;
import com.sun.jna.platform.win32.*;
import com.sun.jna.*;

public class CWMouseHook {
    public final User32   USER32INST;
    public final Kernel32 KERNEL32INST;

    public CWMouseHook() {
        if (!Platform.isWindows()) {
            throw new UnsupportedOperationException("Not supported on this platform.");
        }
        USER32INST   = User32.INSTANCE;
        KERNEL32INST = Kernel32.INSTANCE;
        mouseHook    = hookTheMouse();
        Native.setProtected(true);

    }

    public static LowLevelMouseProc mouseHook;
    public HHOOK                    hhk;
    public Thread                   thrd;
    public boolean                  threadFinish   = true;
    public boolean                  isHooked       = false;
    public static final int         WM_MOUSEMOVE   = 512;
    public static final int         WM_LBUTTONDOWN = 513;
    public static final int         WM_LBUTTONUP   = 514;
    public static final int         WM_RBUTTONDOWN = 516;
    public static final int         WM_RBUTTONUP   = 517;
    public static final int         WM_MBUTTONDOWN = 519;
    public static final int         WM_MBUTTONUP   = 520;

    public void unsetMouseHook() {
        threadFinish = true;
        if (thrd.isAlive()) {
            thrd.interrupt();
            thrd = null;
        }
        isHooked = false;
    }

    public boolean isIsHooked() {
        return isHooked;
    }

    public void setMouseHook() {
        thrd         = new Thread(new Runnable() {
                         @Override
                         public void run() {
                             try {
                                 if (!isHooked) {
                                     hhk      = USER32INST.SetWindowsHookEx(14, mouseHook,
                                             KERNEL32INST.GetModuleHandle(null), 0);
                                     isHooked = true;
                                     MSG msg = new MSG();
                                     while ((USER32INST.GetMessage(msg, null, 0, 0)) != 0) {
                                         USER32INST.TranslateMessage(msg);
                                         USER32INST.DispatchMessage(msg);
                                         System.out.print(isHooked);
                                         if (!isHooked)
                                             break;
                                     }
                                 } else
                                     System.out.println("The Hook is already installed.");
                             } catch (Exception e) {
                                 System.err.println(e.getMessage());
                                 System.err.println("Caught exception in MouseHook!");
                             }
                         }
                     },
                "Named thread");
        threadFinish = false;
        thrd.start();

    }

    private interface LowLevelMouseProc extends HOOKPROC {
        LRESULT callback(int nCode, WPARAM wParam, MOUSEHOOKSTRUCT lParam);
    }

    public LowLevelMouseProc hookTheMouse() {
        return new LowLevelMouseProc() {
            @Override
            public LRESULT callback(int nCode, WPARAM wParam, MOUSEHOOKSTRUCT info) {
                if (nCode >= 0) {
                    switch (wParam.intValue()) {
                    case CWMouseHook.WM_LBUTTONDOWN:
                        // do stuff
                        break;
                    case CWMouseHook.WM_RBUTTONDOWN:
                        // do stuff
                        break;
                    case CWMouseHook.WM_MBUTTONDOWN:
                        // do other stuff
                        break;
                    case CWMouseHook.WM_LBUTTONUP:
                        // do even more stuff
                        break;
                    case CWMouseHook.WM_MOUSEMOVE:

                        break;
                    default:
                        break;
                    }
                    /****************************
                     * DO NOT CHANGE, this code unhooks mouse
                     *********************************/
                    if (threadFinish == true) {
                        USER32INST.PostQuitMessage(0);
                    }
                    /***************************
                     * END OF UNCHANGABLE
                     *******************************************************/
                }
                return USER32INST.CallNextHookEx(hhk, nCode, wParam,
                        new LPARAM(Pointer.nativeValue(info.getPointer())));
            }
        };
    }

    public class Point extends Structure {
        public class ByReference extends Point implements Structure.ByReference {
        };

        public NativeLong x;
        public NativeLong y;
    }

    public static class MOUSEHOOKSTRUCT extends Structure {
        public static class ByReference extends MOUSEHOOKSTRUCT implements Structure.ByReference {
        };

        public POINT     pt;
        public HWND      hwnd;
        public int       wHitTestCode;
        public ULONG_PTR dwExtraInfo;
    }
}
