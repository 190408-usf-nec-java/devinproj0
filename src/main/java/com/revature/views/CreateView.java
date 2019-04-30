package com.revature.views;

public class CreateView implements View {

	@Override
	public View printOptions() {
		System.out.println("Please input your new username");
		return new CreateView();
	}

}
