package com.cri.contextmenuexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.View;
import android.webkit.WebView.*;
import android.webkit.WebView;
import android.view.MenuItem;
import android.widget.Toast;
import android.view.ActionMode;
import android.view.Menu;
public class MainActivity extends AppCompatActivity implements MenuItem.OnMenuItemClickListener{
    WebView webView;
	ActionMode mActionMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
        webView=findViewById(R.id.activity_mainWebView);
		webView.loadUrl("https://www.google.com");
		registerForContextMenu(webView);
    }
	private WebView.HitTestResult result;

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		result = webView.getHitTestResult();
		if (result.getType() == HitTestResult.ANCHOR_TYPE
			|| result.getType() == HitTestResult.SRC_ANCHOR_TYPE) {
			menu.setHeaderTitle(result.getExtra());
			menu.add(0, 1, 0, "Copy Link").setOnMenuItemClickListener(MainActivity.this);
			menu.add(0, 2, 0, "Open in Other Browser").setOnMenuItemClickListener(this);
		} else if (result.getType() == HitTestResult.IMAGE_TYPE
				   || result.getType() == HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
			menu.setHeaderTitle(result.getExtra());
			menu.add(0, 3, 0, "Save Image").setOnMenuItemClickListener(this);
			menu.add(0, 4, 0, "Share Image").setOnMenuItemClickListener(this);
		}
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		if (item.getItemId() == 1) {
				Toast.makeText(getApplicationContext(), "Link Copied", Toast.LENGTH_SHORT).show();
			
		} else if (item.getItemId() == 2) {
			Toast.makeText(getApplicationContext(), "Open in Other Browser", Toast.LENGTH_SHORT).show();
		} else if (item.getItemId() == 3) {
			Toast.makeText(getApplicationContext(), "Save Image", Toast.LENGTH_SHORT).show();
			
		} else if (item.getItemId() == 4) {
			Toast.makeText(getApplicationContext(), "Share Image", Toast.LENGTH_SHORT).show();
			
		}

		return true;
	}

	@Override
	public void onActionModeStarted(ActionMode mode) {
		if (mActionMode == null) {
			mActionMode = mode;
			Menu menu = mode.getMenu();
			
		}
	}

	@Override
	public void onActionModeFinished(ActionMode mode) {
		mActionMode = null;
		super.onActionModeFinished(mode);
	}
	}

