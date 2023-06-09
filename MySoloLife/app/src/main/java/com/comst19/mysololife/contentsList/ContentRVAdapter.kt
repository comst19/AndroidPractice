package com.comst19.mysololife.contentsList

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.comst19.mysololife.R
import com.comst19.mysololife.utils.FBAuth
import com.comst19.mysololife.utils.FBRef

class ContentRVAdapter(val context : Context,
                       val items: ArrayList<ContentModel>,
                       val keyList : ArrayList<String>,
                       val bookmarkIdList : MutableList<String>)
    : RecyclerView.Adapter<ContentRVAdapter.ViewHolder>() {

//    interface ItemClick{
//        fun onClick(view : View, position: Int)
//    }
//    var itemClick : ItemClick? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentRVAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_rv_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContentRVAdapter.ViewHolder, position: Int) {
//        if (itemClick != null){
//            holder.itemView.setOnClickListener{ v->
//                itemClick?.onClick(v, position)
//
//            }
//        }
        holder.bindItems(items[position], keyList[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        fun bindItems(item : ContentModel, key : String){

            itemView.setOnClickListener{
                val intent = Intent(context, ContentShowActivity::class.java)
                intent.putExtra("url",item.webUrl)
                itemView.context.startActivity(intent)
            }

            val contentTitle = itemView.findViewById<TextView>(R.id.textArea)
            val imageViewArea = itemView.findViewById<ImageView>(R.id.imageArea)
            val bookmarkArea = itemView.findViewById<ImageView>(R.id.bookmarkArea)

            if(bookmarkIdList.contains(key)){
                bookmarkArea.setImageResource(R.drawable.bookmark_color)
            }else{
                bookmarkArea.setImageResource(R.drawable.bookmark_white)
            }

            bookmarkArea.setOnClickListener{

                if(bookmarkIdList.contains(key)){
                    // 북마크 삭제
                    //bookmarkIdList.remove(key)
                    Toast.makeText(context, "북마크에 해제되었습니다.", Toast.LENGTH_LONG).show()
                    FBRef.bookmarkRef
                        .child(FBAuth.getUid())
                        .child(key)
                        .removeValue()
                }else{
                    // 북마크 추가
                    Toast.makeText(context, "북마크에 추가되었습니다.", Toast.LENGTH_LONG).show()
                    FBRef.bookmarkRef
                        .child(FBAuth.getUid())
                        .child(key)
                        .setValue(BookmarkModel(true))
                }

            }

            contentTitle.text = item.title
            Glide.with(context)
                .load(item.imageUrl)
                .into(imageViewArea)
        }
    }

}