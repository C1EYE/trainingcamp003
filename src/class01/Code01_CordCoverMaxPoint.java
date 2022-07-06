package class01;

import java.util.Arrays;

public class Code01_CordCoverMaxPoint {

    public static int maxPoint1(int[] arr, int L) {
//		int res = 1;
//		for (int i = 0; i < arr.length; i++) {
//			int nearest = nearestIndex(arr, i, arr[i] - L);
//			res = Math.max(res, i - nearest + 1);
//		}
//		return res;

        int res = 1;
        for (int i = 0; i < arr.length; i++) {
            int nearest = nearestIndex(arr, i, arr[i] - L);
            res = Math.max(res, i - nearest + 1);
        }
        return res;
    }

    // 二分查找左侧第一个不小于左侧边界的值的index
    public static int nearestIndex(int[] arr, int R, int value) {
//		int L = 0;
//		int index = R;
//		while (L <= R) {
//			int mid = L + ((R - L) >> 1);
//			if (arr[mid] >= value) {
//				index = mid;
//				R = mid - 1;
//			} else {
//				L = mid + 1;
//			}
//		}
//		return index;

        // 我的版本
        // 左侧边界
        int L = 0;
        while (L < R) {
            int mid = L + R >> 1;
            if (arr[mid] >= value) {
                R = mid;
            } else {
                L = mid + 1;
            }
        }
        return L;
    }

    // 滑动窗口
    public static int maxPoint2(int[] arr, int L) {
//		int left = 0;
//		int right = 0;
//		int N = arr.length;
//		int max = 0;
//		while (left < N) {
//			while (right < N && arr[right] - arr[left] <= L) {
//				right++;
//			}
//			max = Math.max(max, right - (left++));
//		}
//		return max;

        int left = 0;
        int right = 0;
        int N = arr.length;
        int max = 0;
        // [L,R)
        while (left < N) {
            while (right < N && arr[right] - arr[left] <= L) {
                right++;
            }
            max = Math.max(max, right - (left++));
        }
        return max;
    }

    // for test
    public static int test(int[] arr, int L) {
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i - 1;
            while (pre >= 0 && arr[i] - arr[pre] <= L) {
                pre--;
            }
            max = Math.max(max, i - pre);
        }
        return max;
    }

    // for test
    public static int[] generateArray(int len, int max) {
        int[] ans = new int[(int) (Math.random() * len) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (int) (Math.random() * max);
        }
        Arrays.sort(ans);
        return ans;
    }

    public static void main(String[] args) {
        int len = 100;
        int max = 1000;
        int testTime = 100000;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int L = (int) (Math.random() * max);
            int[] arr = generateArray(len, max);
            int ans1 = maxPoint1(arr, L);
            int ans2 = maxPoint2(arr, L);
            int ans3 = test(arr, L);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("oops!");
                break;
            }
        }

    }

}
