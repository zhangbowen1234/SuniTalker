//package com.silver.chat.ui.contact;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.GridView;
//import android.widget.TextView;
//
//import com.silver.chat.network.responsebean.ContactListBean;
//
///**
// * Created by lenovo on 2017/5/4.
// */
//
//public class HomeRecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//
//
//    public static final String GOODS_BEAN = "goods_bean";
//
//    /**
//
//     * 上下文
//
//     */
//
//    private Context mContext;
//
//    /**
//
//     * 数据Bean对象
//
//     */
//
//    private ContactListBean resultBean;
//
//    /**
//
//     * 五种类型
//
//     */
//
//    /**
//
//     * 横幅广告
//
//     */
//
//    public static final int BANNER = 0;
//
//    /**
//
//     * 频道
//
//     */
//
//    public static final int CHANNEL = 1;
//
//
//
//    /**
//
//     * 活动
//
//     */
//
//    public static final int ACT = 2;
//
//
//
//    /**
//
//     * 秒杀
//
//     */
//
//    public static final int SECKILL = 3;
//
//    /**
//
//     * 推荐
//
//     */
//
//    public static final int RECOMMEND = 4;
//
//    /**
//
//     * 热卖
//
//     */
//
//    public static final int HOT = 5;
//
//
//
//    /**
//
//     * 当前类型
//
//     */
//
//    public int currentType = BANNER;
//
//    private final LayoutInflater mLayoutInflater;
//
//
//
//
//
//
//
//    public HomeRecycleAdapter(Context mContext, ContactListBean res2ultBean) {
//
//        this.mContext = mContext;
//
//        this.resultBean = resultBean;
//
//        mLayoutInflater = LayoutInflater.from(mContext);
//
//    }
//
//
//
//    /**
//
//     * 根据位置得到类型-系统调用
//
//     * @param position
//
//     * @return
//
//     */
//
//    @Override
//
//    public int getItemViewType(int position) {
//
//        switch (position) {
//
//            case BANNER:
//
//                currentType = BANNER;
//
//                break;
//
//            case CHANNEL:
//
//                currentType = CHANNEL;
//
//                break;
//
//            case ACT:
//
//                currentType = ACT;
//
//                break;
//
//            case SECKILL:
//
//                currentType = SECKILL;
//
//                break;
//
//            case RECOMMEND:
//
//                currentType = RECOMMEND;
//
//                break;
//
//            case HOT:
//
//                currentType = HOT;
//
//                break;
//
//        }
//
//        return currentType;
//
//    }
//
//
//
//    /**
//
//     * 返回总条数，共六种类型
//
//     * @return
//
//     */
//
//    @Override
//
//    public int getItemCount() {
//
//        return 6;
//
//    }
//
//
//
//    @Override
//
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//
//        if (viewType == BANNER) {
//
//            return new BannerViewHolder(mLayoutInflater.inflate(R.layout.group_chat_layout, null), mContext, resultBean);
//
//        } else if (viewType == CHANNEL) {
//
//            return new ChannelViewHolder(mLayoutInflater.inflate(R.layout.channel_item, null), mContext);
//
//        } else if (viewType == ACT) {
//
//            return new ActViewHolder(mLayoutInflater.inflate(R.layout.act_item, null), mContext);
//
//        } else if (viewType == SECKILL) {
//
//            return new SeckillViewHolder(mLayoutInflater.inflate(R.layout.seckill_item, null), mContext);
//
//        } else if (viewType == RECOMMEND) {
//
//            return new RecommendViewHolder(mLayoutInflater.inflate(R.layout.recommend_item, null), mContext);
//
//        } else if (viewType == HOT) {
//
//            return new HotViewHolder(mLayoutInflater.inflate(R.layout.hot_item, null), mContext);
//
//        }
//
//        return null;
//
//    }
//
//
//
//    /**
//
//     * 绑定数据
//
//     * @param holder
//
//     * @param position
//
//     */
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//
//        if (getItemViewType(position) == BANNER) {
//
//            BannerViewHolder bannerViewHolder = (BannerViewHolder) holder;
//
//            bannerViewHolder.setData(resultBean.getAvatar());
//
//        } else if (getItemViewType(position) == CHANNEL) {
//
//            ChannelViewHolder channelViewHolder = (ChannelViewHolder) holder;
//
//            channelViewHolder.setData(resultBean.getChannel_info());
//
//        } else if (getItemViewType(position) == ACT) {
//
//            ActViewHolder actViewHolder = (ActViewHolder) holder;
//
//            actViewHolder.setData(resultBean.getAct_info());
//
//        } else if (getItemViewType(position) == SECKILL) {
//
//            SeckillViewHolder seckillViewHolder = (SeckillViewHolder) holder;
//
//            seckillViewHolder.setData(resultBean.getSeckill_info());
//
//        } else if (getItemViewType(position) == RECOMMEND) {
//
//            RecommendViewHolder recommendViewHolder = (RecommendViewHolder) holder;
//
//            recommendViewHolder.setData(resultBean.getRecommend_info());
//
//        } else if (getItemViewType(position) == HOT) {
//
//            HotViewHolder hotViewHolder = (HotViewHolder) holder;
//
//            hotViewHolder.setData(resultBean.getHot_info());
//
//        }
//
//    }
//
//
//
//    class HotViewHolder extends RecyclerView.ViewHolder {
//
//        private TextView tv_more_hot;
//
//        private GridView gv_hot;
//
//        private Context mContext;
//
//
//
//        public HotViewHolder(View itemView, Context mContext) {
//
//            super(itemView);
//
//            tv_more_hot = (TextView) itemView.findViewById(R.id.tv_more_hot);
//
//            gv_hot = (GridView) itemView.findViewById(R.id.gv_hot);
//
//            this.mContext = mContext;
//
//        }
//
//
//
//        public void setData(final List<ResultBean.HotInfoBean> data) {
//
//            HotGridViewAdapter adapter = new HotGridViewAdapter(mContext, data);
//
//            gv_hot.setAdapter(adapter);
//
//
//
//            //点击事件
//
//            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//                @Override
//
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                    // Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
//
//                    String cover_price = data.get(position).getCover_price();
//
//                    String name = data.get(position).getName();
//
//                    String figure = data.get(position).getFigure();
//
//                    String product_id = data.get(position).getProduct_id();
//
//                    GoodsBean goodsBean = new GoodsBean(name, cover_price, figure, product_id);
//
//
//
//                    Intent intent = new Intent(mContext, GoodsInfoActivity.class);
//
//                    intent.putExtra(GOODS_BEAN, goodsBean);
//
//                    mContext.startActivity(intent);
//
//                }
//
//            });
//
//        }
//
//    }
//
