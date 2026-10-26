package com.pspx.app;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
    private LinearLayout games;
    private static final int PICK_GAME = 42;
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(24, 32, 24, 24);
        root.setBackgroundColor(Color.rgb(12, 17, 33));
        TextView title = new TextView(this);
        title.setText("🎮 PSP X"); title.setTextSize(30); title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER); root.addView(title);
        TextView hint = new TextView(this);
        hint.setText("مكتبة ألعاب PSP — نسخة تجريبية");
        hint.setTextColor(Color.LTGRAY); hint.setGravity(Gravity.CENTER);
        root.addView(hint);
        Button add = new Button(this); add.setText("+ إضافة لعبة ISO / CSO");
        root.addView(add);
        games = new LinearLayout(this); games.setOrientation(LinearLayout.VERTICAL);
        ScrollView scroll = new ScrollView(this); scroll.addView(games);
        root.addView(scroll);
        add.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("*/*"); intent.addCategory(Intent.CATEGORY_OPENABLE);
            startActivityForResult(intent, PICK_GAME);
        });
        setContentView(root);
    }
    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != PICK_GAME || resultCode != RESULT_OK || data == null) return;
        Uri uri = data.getData(); if (uri == null) return;
        String name = uri.getLastPathSegment();
        if (name == null) name = "PSP game";
        String lower = name.toLowerCase(java.util.Locale.ROOT);
        if (!lower.endsWith(".iso") && !lower.endsWith(".cso")) {
            Toast.makeText(this, "اختار ملف ISO أو CSO", Toast.LENGTH_LONG).show(); return;
        }
        try { getContentResolver().takePersistableUriPermission(uri, Intent.FLAG_GRANT_READ_URI_PERMISSION); }
        catch (SecurityException ignored) { }
        Button game = new Button(this); game.setText("🎮 " + name + "  ▶"); games.addView(game);
        game.setOnClickListener(v -> Toast.makeText(this, "تمت إضافة اللعبة. التشغيل يحتاج دمج محرك PPSSPP الأصلي.", Toast.LENGTH_LONG).show());
    }
}