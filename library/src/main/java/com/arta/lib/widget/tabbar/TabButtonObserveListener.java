package com.arta.lib.widget.tabbar;

import java.util.ArrayList;
import java.util.List;

public class TabButtonObserveListener implements ITabButtonObserveListener {

	private List<TabButton> tabButtonList;

	@Override
	public void addObserveElement(TabButton tabButton) {
		if(tabButtonList == null){
			tabButtonList = new ArrayList<TabButton>();
		}
		if(!tabButtonList.contains(tabButton)){
			tabButtonList.add(tabButton);
		}
	}

	@Override
	public void stateChange(TabButton changedButton, int index) {
		for(int i = 0; i < tabButtonList.size(); i++){
			if(i == index){
				changedButton.setTabToggle(true);
				continue;
			}
			tabButtonList.get(i).setTabToggle(false);
		}
	}

}