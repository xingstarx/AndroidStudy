package com.ztiany.android.drawer;


import com.android.base.fragment.FragmentInfo;
import com.android.base.fragment.FragmentPager;
import com.ztiany.android.R;

/**
 * Author Ztiany      <br/>
 * Email ztiany3@gmail.com      <br/>
 * Date 2016-03-19 15:09      <br/>
 * Description：
 */
class MainPages extends FragmentPager {


    static MainPages getMainPages() {
        return new MainPages();
    }

    private MainPages() {
        add(
                FragmentInfo.builder().clazz(AllGanKTypeFragment.class)
                        .pagerId(R.id.id_menu_nav_all)
                        .tag("AllGanKTypeFragment_Main")
                        .toStack(false)
                        .titleId(-1)
                        .build()
        );

        add(
                FragmentInfo.builder().clazz(CategoryFragment.class)
                        .pagerId(R.id.id_menu_nav_category)
                        .toStack(true)
                        .tag("CategoryFragment_Main")
                        .stackName("CategoryFragment_Stack_Name")
                        .titleId(-1)
                        .build()
        );

        add(
                FragmentInfo.builder().clazz(RandomFragment.class)
                        .pagerId(R.id.id_menu_nav_random)
                        .toStack(true)
                        .tag("RandomFragment_Main")

                        .stackName("RandomFragment_Stack_Name")
                        .titleId(-1)
                        .build()
        );

        add(
                FragmentInfo.builder().clazz(GirlsFragment.class)
                        .pagerId(R.id.id_menu_nav_girls)
                        .tag("GirlsFragment_Main")
                        .toStack(true)
                        .stackName("GirlsFragment_Stack_Name")
                        .titleId(-1)
                        .build()
        );
    }


}
