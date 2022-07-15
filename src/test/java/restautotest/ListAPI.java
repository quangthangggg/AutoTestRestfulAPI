package restautotest;

import java.util.Scanner;

public class ListAPI {

	public void display() {
		System.out.println("Danh sách các API đã có unit test, nhấn phím để chọn ");
		System.out.println("==============API==============\r\n"
				+ "1. login\r\n"
				+ "2. sign_up\r\n"
				+ "3. edit_account\r\n"
				+ "4. logout\r\n"
				+ "5. get_list_auctions\r\n"
				+ "6. get_list_auctions_by_status\r\n"
				+ "7. get_list_auctions_by_user\r\n"
				+ "8. get_list_auctions_by_type\r\n"
				+ "9. get_detail_auction\r\n"
				+ "10. Create_auction\r\n"
				+ "11. edit_auction\r\n"
				+ "12. create_item\r\n"
				+ "13. create_comment\r\n"
				+ "14. get_list_comments\r\n"
				+ "15. create_bid\r\n"
				+ "16. get_list_bids\r\n"
				+ "17. get_list_categoires\r\n"
				+ "18. get_list_brands\r\n"
				+ "19. accept_max_bid\r\n"
				+ "20. contact_us\r\n"
				+ "21. like_auction\r\n"
				+ "22. get_list_likes\r\n"
				+ "23. total_likes_of_auction\r\n"
				+ "24. get_news\r\n"
				+ "25. read_new\r\n"
				+ "26. get_notifications\r\n"
				+ "27. read_notifications\r\n"
				+ "28. get_slider\r\n"
				+ "29. search\r\n"
				+ "30. delete_comment\r\n"
				+ "");
	}
	
	public String select() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Chọn đi: ");
		String a = sc.nextLine();
		return a;
	}
}
