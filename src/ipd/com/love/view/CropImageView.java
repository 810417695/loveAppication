package ipd.com.love.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import ipd.com.love.R;

public class CropImageView extends View {
	private float mX_1 = 0;
	private float mY_1 = 0;
	private final int STATUS_SINGLE = 1;
	private final int STATUS_MULTI_START = 2;
	private final int STATUS_MULTI_TOUCHING = 3;
	private int mStatus = STATUS_SINGLE;
	private int cropWidth;
	private int cropHeight;
	private final int EDGE_LT = 1;
	private final int EDGE_RT = 2;
	private final int EDGE_LB = 3;
	private final int EDGE_RB = 4;
	private final int EDGE_MOVE_IN = 5;
	private final int EDGE_MOVE_OUT = 6;
	private final int EDGE_NONE = 7;

	public int currentEdge = EDGE_NONE;

	protected float oriRationWH = 0;
	protected final float maxZoomOut = 5.0f;
	protected final float minZoomIn = 0.333333f;

	protected Drawable mDrawable;
	protected FloatDrawable mFloatDrawable;

	protected Rect mDrawableSrc = new Rect();// 閸ュ墽澧朢ect閸欐ɑ宕查弮鍓佹畱Rect
	protected Rect mDrawableDst = new Rect();// 閸ュ墽澧朢ect
	protected Rect mDrawableFloat = new Rect();// 濞搭喖鐪伴惃鍑磂ct
	protected boolean isFrist = true;
	private boolean isTouchInSquare = true;

	protected Context mContext;

	public CropImageView(Context context) {
		super(context);
		init(context);
	}

	public CropImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CropImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);

	}

	@SuppressLint("NewApi")
	private void init(Context context) {
		this.mContext = context;
		try {
			if (android.os.Build.VERSION.SDK_INT >= 11) {
				this.setLayerType(LAYER_TYPE_SOFTWARE, null);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mFloatDrawable = new FloatDrawable(context);
	}

	public void setDrawable(Drawable mDrawable, int cropWidth, int cropHeight) {
		this.mDrawable = mDrawable;
		this.cropWidth = cropWidth;
		this.cropHeight = cropHeight;
		this.isFrist = true;
		invalidate();
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getPointerCount() > 1) {
			if (mStatus == STATUS_SINGLE) {
				mStatus = STATUS_MULTI_START;
			} else if (mStatus == STATUS_MULTI_START) {
				mStatus = STATUS_MULTI_TOUCHING;
			}
		} else {
			if (mStatus == STATUS_MULTI_START || mStatus == STATUS_MULTI_TOUCHING) {
				mX_1 = event.getX();
				mY_1 = event.getY();
			}

			mStatus = STATUS_SINGLE;
		}

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mX_1 = event.getX();
			mY_1 = event.getY();
			currentEdge = getTouch((int) mX_1, (int) mY_1);
			isTouchInSquare = mDrawableFloat.contains((int) event.getX(), (int) event.getY());

			break;

		case MotionEvent.ACTION_UP:
			checkBounds();
			break;

		case MotionEvent.ACTION_POINTER_UP:
			currentEdge = EDGE_NONE;
			break;

		case MotionEvent.ACTION_MOVE:
			if (mStatus == STATUS_MULTI_TOUCHING) {

			} else if (mStatus == STATUS_SINGLE) {
				int dx = (int) (event.getX() - mX_1);
				int dy = (int) (event.getY() - mY_1);

				mX_1 = event.getX();
				mY_1 = event.getY();
				if (!(dx == 0 && dy == 0)) {
					switch (currentEdge) {
					// 閸欐牗绉锋潏纭咁潡閹峰鍑�
					// case EDGE_LT:
					// mDrawableFloat.set(mDrawableFloat.left + dx,
					// mDrawableFloat.top + dy, mDrawableFloat.right,
					// mDrawableFloat.bottom);
					// break;
					//
					// case EDGE_RT:
					// mDrawableFloat.set(mDrawableFloat.left,
					// mDrawableFloat.top + dy, mDrawableFloat.right + dx,
					// mDrawableFloat.bottom);
					// break;
					//
					// case EDGE_LB:
					// mDrawableFloat.set(mDrawableFloat.left + dx,
					// mDrawableFloat.top, mDrawableFloat.right,
					// mDrawableFloat.bottom + dy);
					// break;
					//
					// case EDGE_RB:
					// mDrawableFloat.set(mDrawableFloat.left,
					// mDrawableFloat.top, mDrawableFloat.right + dx,
					// mDrawableFloat.bottom + dy);
					// break;

					case EDGE_MOVE_IN:
						if (isTouchInSquare) {
							mDrawableFloat.offset((int) dx, (int) dy);
						}
						break;

					case EDGE_MOVE_OUT:
						break;
					}
					mDrawableFloat.sort();
					invalidate();
				}
			}
			break;
		}

		return true;
	}

	// 閺嶈宓侀崚婵娦曢幗鍝ュ仯閸掋倖鏌囬弰顖澬曢幗鍝ユ畱Rect閸濐亙绔存稉顏囶潡
	public int getTouch(int eventX, int eventY) {
		if (mFloatDrawable.getBounds().left <= eventX
				&& eventX < (mFloatDrawable.getBounds().left + mFloatDrawable.getBorderWidth())
				&& mFloatDrawable.getBounds().top <= eventY
				&& eventY < (mFloatDrawable.getBounds().top + mFloatDrawable.getBorderHeight())) {
			return EDGE_LT;
		} else if ((mFloatDrawable.getBounds().right - mFloatDrawable.getBorderWidth()) <= eventX
				&& eventX < mFloatDrawable.getBounds().right && mFloatDrawable.getBounds().top <= eventY
				&& eventY < (mFloatDrawable.getBounds().top + mFloatDrawable.getBorderHeight())) {
			return EDGE_RT;
		} else if (mFloatDrawable.getBounds().left <= eventX
				&& eventX < (mFloatDrawable.getBounds().left + mFloatDrawable.getBorderWidth())
				&& (mFloatDrawable.getBounds().bottom - mFloatDrawable.getBorderHeight()) <= eventY
				&& eventY < mFloatDrawable.getBounds().bottom) {
			return EDGE_LB;
		} else if ((mFloatDrawable.getBounds().right - mFloatDrawable.getBorderWidth()) <= eventX
				&& eventX < mFloatDrawable.getBounds().right
				&& (mFloatDrawable.getBounds().bottom - mFloatDrawable.getBorderHeight()) <= eventY
				&& eventY < mFloatDrawable.getBounds().bottom) {
			return EDGE_RB;
		} else if (mFloatDrawable.getBounds().contains(eventX, eventY)) {
			return EDGE_MOVE_IN;
		}
		return EDGE_MOVE_OUT;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		if (mDrawable == null) {
			return;
		}

		if (mDrawable.getIntrinsicWidth() == 0 || mDrawable.getIntrinsicHeight() == 0) {
			return;
		}

		configureBounds();
		// 閸︺劎鏁剧敮鍐х瑐閼哄崬娴橀悧?
		mDrawable.draw(canvas);
		canvas.save();
		// 閸︺劎鏁剧敮鍐х瑐閻㈢粯璇炵仦渚砽oatDrawable,Region.Op.DIFFERENCE閺勵垵銆冪粈绡焑ct娴溿倝娉﹂惃鍕夐梿?
		canvas.clipRect(mDrawableFloat, Region.Op.DIFFERENCE);
		// 閸︺劋姘﹂梿鍡欐畱鐞涖儵娉︽稉濠勬暰娑撳﹦浼嗛懝鑼暏閺夈儱灏崚?
		canvas.drawColor(Color.parseColor("#a0000000"));
		canvas.restore();
		// 閻㈢粯璇炵仦?
		mFloatDrawable.draw(canvas);
	}

	protected void configureBounds() {
		// configureBounds閸︹暙nDraw閺傝纭舵稉顓＄殶閻�?
		// isFirst閻ㄥ嫮娲伴惃鍕Ц娑撳娼扮�电DrawableSrc閸滃DrawableFloat閸欘亜鍨垫慨瀣娑�?濞嗏槄绱�
		// 娑斿鎮楅惃鍕綁閸栨牗妲搁弽瑙勫祦touch娴滃娆㈤弶銉ュ綁閸栨牜娈戦敍宀�?灞肩瑝閺勵垱鐦″▎鈩冨⒔鐞涘矂鍣搁弬鏉款嚠mDrawableSrc閸滃DrawableFloat鏉╂稖顢戠拋鍓х枂
		if (isFrist) {
			oriRationWH = ((float) mDrawable.getIntrinsicWidth()) / ((float) mDrawable.getIntrinsicHeight());
			
			final float scale = mContext.getResources().getDisplayMetrics().density;
			int w = Math.min(getWidth(), (int) (mDrawable.getIntrinsicWidth() * scale + 0.5f));
			int h = (int) (w / oriRationWH);

			int left = (getWidth() - w) / 2;
			int top = (getHeight() - h) / 2;
			int right = left + w;
			int bottom = top + h;

			mDrawableSrc.set(left, top, right, bottom);
			mDrawableDst.set(mDrawableSrc);

			int floatHeight = (int) getResources().getDimension(R.dimen.detail_img_height);

			int floatTop = (getMeasuredHeight() - floatHeight) / 2;

			mDrawableFloat.set(0, floatTop, getMeasuredWidth(), floatTop + floatHeight);

			isFrist = false;
		}

		mDrawable.setBounds(mDrawableDst);
		mFloatDrawable.setBounds(mDrawableFloat);
	}

	// 閸︹暠p娴滃娆㈡稉顓＄殶閻€劋绨＄拠銉︽煙濞夋洩绱濋惄顔炬畱閺勵垱顥呴弻銉︽Ц閸氾附濡稿ù顔肩湴閹锋牕鍤禍鍡楃潌楠�?
	protected void checkBounds() {
		
		int newLeft = mDrawableFloat.left;
		int newTop = mDrawableFloat.top;

		boolean isChange = false;
		if (mDrawableFloat.left < getLeft()) {
			newLeft = getLeft();
			isChange = true;
		}

		if (mDrawableFloat.top < getTop()) {
			newTop = getTop();
			isChange = true;
		}

		if (mDrawableFloat.right > getRight()) {
			newLeft = getRight() - mDrawableFloat.width();
			isChange = true;
		}

		if (mDrawableFloat.bottom > getBottom()) {
			newTop = getBottom() - mDrawableFloat.height();
			isChange = true;
		}

		mDrawableFloat.offsetTo(newLeft, newTop);
		if (isChange) {
			invalidate();
		}
	}

	// 鏉╂稖顢戦崶鍓у閻ㄥ嫯顥嗛崜顏庣礉閹�?鐠嬫挾娈戠憗浣稿鐏忚鲸妲搁弽瑙勫祦Drawable閻ㄥ嫭鏌婇惃鍕綏閺嶅洤婀悽璇茬娑撳﹤鍨卞杞扮瀵姵鏌婇惃鍕禈閻�?
	public Bitmap getCropImage() {
		Bitmap tmpBitmap = null;
		Bitmap ret = null;
		Matrix matrix = null;
		try {
			tmpBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Config.RGB_565);
			Canvas canvas = new Canvas(tmpBitmap);
			mDrawable.draw(canvas);

			matrix = new Matrix();
			float scale = (float) (mDrawableSrc.width()) / (float) (mDrawableDst.width());
			matrix.postScale(scale, scale);

			ret = Bitmap.createBitmap(tmpBitmap, mDrawableFloat.left, mDrawableFloat.top, mDrawableFloat.width(),
					mDrawableFloat.height(), matrix, true);

		} catch (Exception e) {

		} finally {
			tmpBitmap.recycle();
			tmpBitmap = null;
		}

		return ret;
	}

	public int dipTopx(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
}
