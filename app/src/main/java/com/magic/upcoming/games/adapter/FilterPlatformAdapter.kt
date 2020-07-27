package com.magic.upcoming.games.adapter

import android.content.Context
import com.magic.upcoming.games.R
import com.magic.upcoming.games.base.BaseBindingAdapter
import com.magic.upcoming.games.constant.Platform
import com.magic.upcoming.games.databinding.PlatformCheckListItemBinding
import com.magic.upcoming.games.orm.OrmGameApi


class FilterPlatformAdapter(mContext: Context) : BaseBindingAdapter<Platform, PlatformCheckListItemBinding>(mContext) {

    override fun getLayoutResId(viewType: Int): Int {
        return R.layout.platform_check_list_item
    }

    override fun onBindItem(binding: PlatformCheckListItemBinding?, item: Platform, position: Int) {
        binding?.status = OrmGameApi.gameFilterOptions?.platformIndices?.contains(position)
        binding?.name = item.fullName
        binding?.platformCheckBox?.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked){
                if (!OrmGameApi.gameFilterOptions?.platformIndices?.contains(position)!!)
                    OrmGameApi.gameFilterOptions?.platformIndices?.add(position)
            }else{
                OrmGameApi.gameFilterOptions?.platformIndices?.remove(position)
            }
        }
    }
}