# Add any ProGuard configurations specific to this
# extension here.

-keep public class in.sonraj.progressstatusbar.ProgressStatusBar {
    public *;
 }
-keeppackagenames gnu.kawa**, gnu.expr**

-optimizationpasses 4
-allowaccessmodification
-mergeinterfacesaggressively

-repackageclasses 'in/sonraj/progressstatusbar/repack'
-flattenpackagehierarchy
-dontpreverify
-dontwarn