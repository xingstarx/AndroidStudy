package com.ztiany.android4.features;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;

import com.ztiany.android4.R;

/**
 * Author Ztiany                   <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-04-10 17:20      <br/>
 * Description：
 */
public class PopupMenuFragment extends Fragment {


    public static PopupMenuFragment newInstance() {
        return new PopupMenuFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_popup_menu, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.frag_xml_popup_show_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPopupMenuFormXml(v);
                    }
                });


        view.findViewById(R.id.frag_code_popup_show_btn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showPopupMenuFormCode(v);
                    }
                });
    }

    private void showPopupMenuFormXml(View anchor) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), anchor);
        Menu menu = popupMenu.getMenu();
        popupMenu.getMenuInflater().inflate(R.menu.menu_main,menu);
        popupMenu.show();
    }

    private void showPopupMenuFormCode(View anchor) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), anchor, Gravity.RIGHT|Gravity.TOP);
        Menu menu = popupMenu.getMenu();
        menu.add(Menu.NONE, 100, 0, "Android2.3");
        menu.add(Menu.NONE, 200, 1, "Android3.0");


        SubMenu subMenu = menu.addSubMenu("Android4.X");
        subMenu.add(Menu.NONE, 300, 0, "Android4.0");
        subMenu.add(Menu.NONE, 301, 1, "Android4.1");
        subMenu.add(Menu.NONE, 302, 2, "Android4.2");
        subMenu.add(Menu.NONE, 303, 3, "Android4.3");
        subMenu.add(Menu.NONE, 304, 4, "Android4.4");


        menu.add(Menu.NONE, 500, 3, "Android5.0");
        menu.add(Menu.NONE, 600, 4, "Android6.0");

        popupMenu.show();
    }


}
