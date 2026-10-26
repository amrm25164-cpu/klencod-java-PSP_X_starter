PSP X - مشروع مبدئي لأندرويد Java

افتح المجلد كمشروع Gradle Android في Klencod IDE.
النسخة الحالية تعرض مكتبة ألعاب وتختار ملفات ISO/CSO فقط.
لا تشغل ألعاب PSP بعد؛ لا يوجد محرك محاكاة مدمج.

للتشغيل داخل التطبيق: يلزم دمج PPSSPP C++ مع Android NDK و JNI ومكتبات arm64-v8a أو armeabi-v7a، مع الالتزام بترخيص المصدر وحقوق الألعاب.
وجود دعم jniLibs وحده لا يكفي لبناء محرك PPSSPP.
قد تحتاج تعديل إصدار Android Gradle Plugin حسب إصدار Gradle المدعوم في IDE.
