(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-50bd92d2"],{2582:function(e,t,s){"use strict";s("ed2c")},"28ba":function(e,t,s){"use strict";s.r(t);var r=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"app-container"},[s("el-form",{ref:"form",attrs:{model:e.form,"label-width":"120px"}},[s("el-form-item",{attrs:{label:"选题名称"}},[s("el-input",{attrs:{readonly:""},model:{value:e.form.topicName,callback:function(t){e.$set(e.form,"topicName",t)},expression:"form.topicName"}})],1),s("el-form-item",{attrs:{label:"选题描述"}},[s("el-input",{attrs:{type:"textarea",readonly:""},model:{value:e.form.topicDescription,callback:function(t){e.$set(e.form,"topicDescription",t)},expression:"form.topicDescription"}})],1),s("el-form-item",{attrs:{label:"选题状态"}},[s("el-tag",{attrs:{type:e.statusTagType}},[e._v(e._s(e.statusMessage))])],1),s("el-form-item",[s("el-button",{attrs:{type:"primary"},on:{click:e.onSubmit}},[e._v("提交")]),s("el-button",{on:{click:e.onCancel}},[e._v("取消")]),s("el-button",{attrs:{type:"success"},on:{click:e.queryTeacher}},[e._v("查询指导老师")])],1)],1)],1)},a=[],c=s("c7eb"),n=s("1da1"),o=s("5530"),i=(s("d3b7"),s("2f62")),u={data:function(){return{form:{topicName:"",topicDescription:""},teacherName:"",teacherComments:"",statusMessage:"",statusTagType:""}},computed:Object(o["a"])({},Object(i["c"])(["userId"])),created:function(){this.fetchTopic()},methods:{fetchTopic:function(){var e=this;return Object(n["a"])(Object(c["a"])().mark((function t(){var s,r,a,n,o,i,u,p;return Object(c["a"])().wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.prev=0,s=e.userId,r={method:"POST",headers:{"User-Agent":"Apifox/1.0.0 (https://apifox.com)"}},t.next=5,fetch("http://localhost:9001/student/getTopic?userId=".concat(s),r);case 5:return a=t.sent,t.next=8,a.json();case 8:n=t.sent,a.ok&&n.success?(o=n.message,i=o.topicName,u=o.topicDescription,p=o.topicStatus,e.form.topicName=i,e.form.topicDescription=u,e.statusMessage=e.getStatusMessage(p),e.statusTagType=e.getStatusTagType(p),e.$message("选题信息加载成功！")):e.$message.error("加载选题信息失败。"),t.next=16;break;case 12:t.prev=12,t.t0=t["catch"](0),e.$message.error("查询选题过程中发生错误。"),console.error(t.t0);case 16:case"end":return t.stop()}}),t,null,[[0,12]])})))()},queryTeacher:function(){var e=this;return Object(n["a"])(Object(c["a"])().mark((function t(){var s,r,a,n;return Object(c["a"])().wrap((function(t){while(1)switch(t.prev=t.next){case 0:return t.prev=0,s=e.userId,r={method:"POST",headers:{"User-Agent":"Apifox/1.0.0 (https://apifox.com)"}},t.next=5,fetch("http://localhost:9001/student/getTeacher?userId=".concat(s),r);case 5:return a=t.sent,t.next=8,a.json();case 8:n=t.sent,a.ok&&n.success?(e.teacherName=n.message.userName||"教师信息未找到",e.teacherComments=n.message.teacherComments||"无评语",e.$message("指导老师信息获取成功！")):e.$message.error("获取指导老师信息失败。"),t.next=16;break;case 12:t.prev=12,t.t0=t["catch"](0),e.$message.error("查询指导老师过程中发生错误。"),console.error(t.t0);case 16:case"end":return t.stop()}}),t,null,[[0,12]])})))()},onSubmit:function(){this.$message("提交功能尚未实现。")},onCancel:function(){this.$message({message:"表单已取消！",type:"warning"})},getStatusMessage:function(e){switch(e){case 0:return"未提交";case 1:return"审核中";case 2:return"通过评审";case 3:return"未通过评审";default:return"未知状态"}},getStatusTagType:function(e){switch(e){case 0:return"info";case 1:return"warning";case 2:return"success";case 3:return"danger";default:return"info"}}}},p=u,m=(s("2582"),s("2877")),l=Object(m["a"])(p,r,a,!1,null,"f50fc0c4",null);t["default"]=l.exports},ed2c:function(e,t,s){}}]);