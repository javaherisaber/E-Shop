package com.example.e_commercesportclothes.adapters;

import ss.com.bannerslider.adapters.SliderAdapter;
import ss.com.bannerslider.viewholder.ImageSlideViewHolder;

public class MainSliderAdapter extends SliderAdapter {

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public void onBindImageSlide(int position, ImageSlideViewHolder viewHolder) {
        switch (position) {
            case 0:
                viewHolder.bindImageSlide("https://www.madelons-sport-shop.de/fileadmin/user_upload/fussball.jpg");
                break;
            case 1:
                viewHolder.bindImageSlide("https://www.sport-shop-speiser.de/wp-content/uploads/Fotolia_146187263_M-1200x800.jpg");
                break;
            case 2:
                viewHolder.bindImageSlide("https://www.sportshop-wuerzburg.com/files/sportshop/uploads/bilder-2017/sport-shop-wuerzburg-01.jpg");
                break;
            case 3:
                viewHolder.bindImageSlide("https://www.grilles-sport-shop.de/images/Laden_2019-01-01/GrillesSportShop_innen04.JPG");
                break;
        }
    }
}
