/*
 * Copyright 2013 David Schreiber
 *           2013 John Paul Nalog
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.arta.lib.widget.fancycoverflow;

import java.util.List;

import com.arta.lib.adapter.AdapterConstant;
import com.arta.lib.adapter.BaseAdapterEntityViewManage;
import com.arta.lib.adapter.BaseEntityViewAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;


public class FancyCoverFlowAdapter<T> extends BaseEntityViewAdapter<T> {

    // =============================================================================
    // Supertype overrides
    // =============================================================================

    public FancyCoverFlowAdapter(Context context, List<T> entityList,
			BaseAdapterEntityViewManage<T> adapterItemManage) {
		super(context, entityList, adapterItemManage);
	}

	@SuppressWarnings("unchecked")
	@Override
    public final View getView(int i, View reusableView, ViewGroup viewGroup) {
        FancyCoverFlow coverFlow = (FancyCoverFlow) viewGroup;

        View wrappedView = null;
        FancyCoverFlowItemWrapper coverFlowItem;

        if (reusableView != null) {
            coverFlowItem = (FancyCoverFlowItemWrapper) reusableView;
            wrappedView = coverFlowItem.getChildAt(0);
            coverFlowItem.removeAllViews();
        } else {
            coverFlowItem = new FancyCoverFlowItemWrapper(viewGroup.getContext());
        }
        
//        wrappedView = this.getCoverFlowItem(i, wrappedView, viewGroup);
        //define start
		EntityViewHolder<T> holder = null;
		if(wrappedView == null){
			wrappedView = adapterItemManage.getAdapterItemView(context, entityList.get(i), i);
			holder = new EntityViewHolder<T>();
			coverFlowItem.setTag(AdapterConstant.TAG_KEY, holder);
		}
		
		holder = (EntityViewHolder<T>) coverFlowItem.getTag(AdapterConstant.TAG_KEY);
		holder.entity = entityList.get(i);
		holder.position = i;
		wrappedView = adapterItemManage.updateAdapterItemView(context, wrappedView, entityList.get(i), i);
        // define end
        
        if (wrappedView == null) {
            throw new NullPointerException("getCoverFlowItem() was expected to return a view, but null was returned.");
        }

        final boolean isReflectionEnabled = coverFlow.isReflectionEnabled();
        coverFlowItem.setReflectionEnabled(isReflectionEnabled);

        if(isReflectionEnabled) {
            coverFlowItem.setReflectionGap(coverFlow.getReflectionGap());
            coverFlowItem.setReflectionRatio(coverFlow.getReflectionRatio());
        }


        coverFlowItem.addView(wrappedView);
        coverFlowItem.setLayoutParams(wrappedView.getLayoutParams());

        return coverFlowItem;
    }
}
