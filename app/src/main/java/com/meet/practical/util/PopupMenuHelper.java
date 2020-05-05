package com.meet.practical.util;

import android.content.Context;
import androidx.appcompat.widget.PopupMenu;
import android.view.Menu;
import android.view.View;

import com.meet.practical.model.entity.eventbus.ModuleSelection;
import com.meet.practical.model.entity.response.ModuleInfo;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class PopupMenuHelper {

    public static void showPopupMenu(Context mContext, View view, List<ModuleInfo> list, int dialogIdentifier) {
        PopupMenu popupMenu = new PopupMenu(mContext, view);
        for (int i = 0; i < list.size(); i++) {
            popupMenu.getMenu().add(Menu.NONE, i, i, list.get(i).getName());
        }

        popupMenu.setOnMenuItemClickListener(menuItem -> {
            int position = menuItem.getItemId();
            ModuleSelection moduleSelection = new ModuleSelection(list.get(position).getId(), list.get(position).getName(), list.get(position).getImage(), dialogIdentifier, true, null);
            EventBus.getDefault().post(moduleSelection);
            return false;
        });

        popupMenu.show();
    }
}
