package com.acastillo.nicestart.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.acastillo.nicestart.fragments.page1;
import com.acastillo.nicestart.fragments.page2;
import com.acastillo.nicestart.fragments.page3;
import com.acastillo.nicestart.fragments.page4;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new page1();
        } else if (position == 1) {
            return new page2();
        } else if (position == 2) {
            return new page3();
        } else if (position == 3) {
            return new page4();
        } else {
            throw new IllegalArgumentException("Invalid position: " + position);
        }
    }

    @Override
    public int getCount() {
        return 4; // Número total de páginas
    }
}
