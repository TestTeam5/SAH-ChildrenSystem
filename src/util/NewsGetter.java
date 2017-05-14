package util;

import model.NewsList;

/*
 * ���ڻ�ȡ����ҳ���25�����ű���
 * ����¼��ǰ��ȡ������������
 */
public class NewsGetter {
	static int index = 0; // �����б����һ�����ŵ���һ�����±�
	public static int newspaper = 0; // ��һ������������ֽ���ͱ��
	static NewsList newsList = Initializer.guangming;
	static Object[][] newsTitles = { new Object[] { "�����ꡰ��������Ҹж����ˡ���ѡ����" }, new Object[] { "ȷ��ũ����Ů���ܹ�������" },
			new Object[] { "ȫ����Эʮ���Ĵλ�����е�����ȫ���������ֳ�ϯ12λίԱΧ�ƿƽ����o�������ҵ..." }, new Object[] { "���ܺ���ũ�����ض�ͯ" },
			new Object[] { "��ũ�塰���ض�ͯ�����������˼��" }, new Object[] { "�����߲�����ϣ�������Ǻ���ʡũ���ʦ�����ж��ƻ�" },
			new Object[] { "��г�����Ұ�µĽ�����ƽ" }, new Object[] { "ũ��ѧ���ѧ�����ܹ�ע�ܼ�̹�����������������������������ս" },
			new Object[] { "ũ�����ض�ͯ����ؽ����ǿ" }, new Object[] { "17��ί�쵼ο���ھ������˿���Ů��ũ�����ض�ͯ" },
			new Object[] { "���ա��߲����ա������������" }, new Object[] { "��У����ũ�彨�����������������ʦ�������ũ����" },
			new Object[] { "�й����񹫰���ѧ�ΰ�ϵ������̫Ԫ���͵ؽ�������������ض�ͯ" }, new Object[] { "�Ӵ󾭷ѱ����ṩ�ص���ָ�������������Դ��ũ����б" },
			new Object[] { "�ֳ�ʮ���������߷ð�ʮ�����尲�մ�ѧ����ѧ��׫д����ũ�嵵����" }, new Object[] { "��ѧ��������Ϊ����ˮ����Ӣ������" },
			new Object[] { "ũ�����ض�ͯ������ذ�" }, new Object[] { "־Ը����������лĻ���ഺ֮�衪�����人��ѧ����Сͤ�¼����Ĺ۲��놙ʾ" },
			new Object[] { "�����˴��ڡ�����ʮ����" }, new Object[] { "��������ͬ�������������" }, new Object[] { "����ũ������ʵ��ȫ����" },
			new Object[] { "����ũ��������Դ�ٽ�����ͳ�﷢չ" }, new Object[] { "ȫ���������˿�ģ��ѡ�˼��" },
			new Object[] { "ȫ���������˿�ģ��ѡ�˼�飨�Ͻ�6�棩" }, new Object[] { "���У����ӵ���ů��԰�����Ϸ�ȫ���ذ�δ�����˵Ĺ���" } };

	public static void init(){
		if(!Initializer.guangming.isEmpty()){
			index = 0;
			newspaper = 0;
			newsList = Initializer.guangming;
		}else if(!Initializer.nanfangdaily.isEmpty()){
			index = 0;
			newspaper = 1;
			newsList = Initializer.nanfangdaily;
		}else if(!Initializer.sichuan.isEmpty()){
			index = 0;
			newspaper = 2;
			newsList = Initializer.sichuan;
		}else{
			index = 0;
			newspaper = 3;
			newsList = null;
		}
	}
	
	// ��ȡ��һҳ���ű���
	public static Object[][] getNews() {
		for (int i = 0; i < 25; i++) {
			if (newsList == null) {
				newsTitles[i] = new Object[] { "" };
			} else {
				newsTitles[i] = new Object[] { newsList.getTitle(index) };
			}
			index++;
			if (newsList != null) {
				if (index >= newsList.size()) {
					index = 0;
					if (newspaper == 0) {
						if (!Initializer.nanfangdaily.isEmpty()) {
							newsList = Initializer.nanfangdaily;
							index = 0;
							newspaper = 1;
						} else if (!Initializer.sichuan.isEmpty()) {
							newsList = Initializer.sichuan;
							index = 0;
							newspaper = 2;
						} else {
							newsList = null;
							index = 0;
							newspaper = 3;
						}
					} else if (newspaper == 1) {
						if(!Initializer.sichuan.isEmpty()){
							newsList = Initializer.sichuan;
							index = 0;
							newspaper = 2;
						}else{
							newsList = null;
							index = 0;
							newspaper = 3;
						}
					}else if (newspaper == 2){
						newsList = null;
						index = 0;
						newspaper = 3;
					}
				}
			}
		}
		return newsTitles;
	}
	
	// ��ȡ��һҳ���ű���
	public static Object[][] getPreviousNews(){
		int sum = 0;
		if(index>=50){
			index -= 50;
		}else{
			if(newspaper >= 3){
				sum = sum + Initializer.guangming.size() + Initializer.nanfangdaily.size() + Initializer.sichuan.size();
			}else if(newspaper == 2){
				sum = sum + Initializer.guangming.size() + Initializer.nanfangdaily.size();
			}else if(newspaper == 1){
				sum = sum + Initializer.guangming.size();
			}
			sum = sum + index - 50;
			if(sum >= Initializer.guangming.size() + Initializer.nanfangdaily.size() + Initializer.sichuan.size()){
				index = sum - Initializer.guangming.size() - Initializer.nanfangdaily.size() - Initializer.sichuan.size();
				newspaper = 3;
				newsList = null;
			}else if(sum >= Initializer.guangming.size() + Initializer.nanfangdaily.size()){
				index = sum - Initializer.guangming.size() - Initializer.nanfangdaily.size();
				newspaper = 2;
				newsList = Initializer.sichuan;
			}else if(sum >= Initializer.guangming.size()){
				index = sum - Initializer.guangming.size();
				newspaper = 1;
				newsList = Initializer.nanfangdaily;
			}else if(sum >= 0){
				index = sum;
				newspaper = 0;
				newsList = Initializer.guangming;
			}else{
				init();
			}
		}
		return getNews();
	}
	
	// ��ȡβҳ���ű���
	public static Object[][] getLastNews(){
		index = Initializer.guangming.size() + Initializer.nanfangdaily.size() + Initializer.sichuan.size();
		index = index % 25;
		if(index != 0){
			index = 25 - index;
		}
		index = index + 25;
		newspaper = 3;
		newsList = null;
		return getPreviousNews();
	}
}
