<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="com.babycard.dao.*,com.babycard.util.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
long languageId = new LanguageHttp().getLanguageId(request);
//Kehu k = new KehuUtil().getKehu(request, response);
%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title><%=Menu.getMenu("parent", languageId) %></title>
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>
	<script type="text/javascript">
		$(function(){
			
		})
	</script>
</head>
<body>
	<div class="page parents">
		<div>
			<img class="bg" src="frame/parents-11.png">
			<div style="background: white;padding: .5rem;margin: 1rem;border-radius: 0.2rem;position: relative;z-index: 2;">
				<h1 style="font-size: .45rem;text-align: center;">用户协议</h1>
				<p style="font-size: .4rem;">
<br/>					欢迎您来到卡片点点。
<br/>
<br/>请您仔细阅读以下条款，如果您对本协议的任何条款表示异议，您可以选择不进入卡片点点。当您注册成功，无论是进入卡片点点，还是在卡片点点上发布任何内容（即「内容」），均意味着您（即「用户」）完全接受本协议项下的全部条款。
<br/>
<br/>使用规则
<br/>
<br/>用户注册成功后，卡片点点将给予每个用户一个用户帐号，该用户帐号由用户负责保管；用户应当对以其用户帐号进行的所有活动和事件负法律责任。
<br/>用户须对在卡片点点的注册信息的真实性、合法性、有效性承担全部责任，用户不得冒充他人；不得利用他人的名义发布任何信息；不得恶意使用注册帐号导致其他用户误认；否则卡片点点有权立即停止提供服务，收回其帐号并由用户独自承担由此而产生的一切法律责任。
<br/>用户直接或通过各类方式（如 RSS 源和站外 API 引用等）间接使用卡片点点服务和数据的行为，都将被视作已无条件接受本协议全部内容；若用户对本协议的任何条款存在异议，请停止使用卡片点点所提供的全部服务。
<br/>卡片点点是一个儿童认知教育、信息分享、传播及获取的平台，用户通过卡片点点发表的信息为公开的信息，其他第三方均可以通过卡片点点获取用户发表的信息，用户对任何信息的发表即认可该信息为公开的信息，并单独对此行为承担法律责任；任何用户不愿被其他第三人获知的信息都不应该在卡片点点上进行发表。
<br/>用户承诺不得以任何方式利用卡片点点直接或间接从事违反中国法律以及社会公德的行为，卡片点点有权对违反上述承诺的内容予以删除。
<br/>用户不得利用卡片点点服务制作、上载、复制、发布、传播或者转载如下内容：
<br/>
<br/>反对宪法所确定的基本原则的；
<br/>危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的；
<br/>损害国家荣誉和利益的；
<br/>煽动民族仇恨、民族歧视，破坏民族团结的；
<br/>侮辱、滥用英烈形象，否定英烈事迹，美化粉饰侵略战争行为的；
<br/>破坏国家宗教政策，宣扬邪教和封建迷信的；
<br/>散布谣言，扰乱社会秩序，破坏社会稳定的；
<br/>散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的；
<br/>侮辱或者诽谤他人，侵害他人合法权益的；
<br/>含有法律、行政法规禁止的其他内容的信息。
<br/>所有用户同意遵守卡片点点社区管理规定（试行）和卡片点点视频服务协议（试行）。
<br/>机构用户同意遵守卡片点点机构号服务协议，以及卡片点点机构号使用规范（试行）。
<br/>卡片点点有权对用户使用卡片点点的情况进行审查和监督，如用户在使用卡片点点时违反任何上述规定，卡片点点或其授权的人有权要求用户改正或直接采取一切必要的措施（包括但不限于更改或删除用户张贴的内容、暂停或终止用户使用卡片点点的权利）以减轻用户不当行为造成的影响。
<br/>
<br/>知识产权
<br/>
<br/>卡片点点是一个信息获取、分享及传播的平台，我们尊重和鼓励卡片点点用户创作的内容，认识到保护知识产权对卡片点点生存与发展的重要性，承诺将保护知识产权作为卡片点点运营的基本原则之一。
<br/>
<br/>用户在卡片点点上发表的全部原创内容（包括但不仅限于回答、文章和评论），著作权均归用户本人所有。用户可授权第三方以任何方式使用，不需要得到卡片点点的同意。
<br/>卡片点点上可由多人参与编辑的内容，包括但不限于问题及补充说明、答案总结、话题描述、话题结构，所有参与编辑者均同意，相关知识产权归卡片点点所有。
<br/>卡片点点提供的网络服务中包含的标识、版面设计、排版方式、文本、图片、图形等均受著作权、商标权及其它法律保护，未经相关权利人（含卡片点点及其他原始权利人）同意，上述内容均不得在任何平台被直接或间接发布、使用、出于发布或使用目的的改写或再发行，或被用于其他任何商业目的。
<br/>为了促进知识的分享和传播，用户将其在卡片点点上发表的全部内容，授予卡片点点免费的、不可撤销的、非独家使用许可，卡片点点有权将该内容用于卡片点点各种形态的产品和服务上，包括但不限于网站以及发表的应用或其他互联网产品。
<br/>第三方若出于非商业目的，将用户在卡片点点上发表的内容转载在卡片点点之外的地方，应当在作品的正文开头的显著位置注明原作者姓名（或原作者在卡片点点上使用的帐号名称），给出原始链接，注明「发表于卡片点点」，并不得对作品进行修改演绎。若需要对作品进行修改，或用于商业目的，第三方应当联系用户获得单独授权，按照用户规定的方式使用该内容。
<br/>卡片点点为用户提供「保留所有权利，禁止转载」的选项。除非获得原作者的单独授权，任何第三方不得转载标注了「禁止转载」的内容，否则均视为侵权。
<br/>在卡片点点上传或发表的内容，用户应保证其为著作权人或已取得合法授权，并且该内容不会侵犯任何第三方的合法权益。如果第三方提出关于著作权的异议，卡片点点有权根据实际情况删除相关的内容，且有权追究用户的法律责任。给卡片点点或任何第三方造成损失的，用户应负责全额赔偿。
<br/>如果任何第三方侵犯了卡片点点用户相关的权利，用户同意授权卡片点点或其指定的代理人代表卡片点点自身或用户对该第三方提出警告、投诉、发起行政执法、诉讼、进行上诉，或谈判和解，并且用户同意在卡片点点认为必要的情况下参与共同维权。
<br/>卡片点点有权但无义务对用户发布的内容进行审核，有权根据相关证据结合《侵权责任法》、《信息网络传播权保护条例》等法律法规及卡片点点社区指导原则对侵权信息进行处理。
<br/>
<br/>个人隐私
<br/>
<br/>尊重用户个人隐私信息的私有性是卡片点点的一贯原则，卡片点点将通过技术手段、强化内部管理等办法充分保护用户的个人隐私信息，除法律或有法律赋予权限的政府部门要求或事先得到用户明确授权等原因外，卡片点点保证不对外公开或向第三方透露用户个人隐私信息，或用户在使用服务时存储的非公开内容。同时，为了运营和改善卡片点点的技术与服务，卡片点点将可能会自行收集使用或向第三方提供用户的非个人隐私信息，这将有助于卡片点点向用户提供更好的用户体验和服务质量。
<br/>
<br/>您使用或继续使用我们的服务，即意味着同意我们按照卡片点点《隐私政策》收集、使用、储存和分享您的相关信息。详情请参见卡片点点《隐私政策》
<br/>
<br/>卡片点点积分
<br/>
<br/>卡片点点积分是专供卡片点点用户在卡片点点平台使用的虚拟币，卡片点点积分可用于卡片点点平台上赞赏、购买和消费内容产品。除此之外，不得用于其他用途。
<br/>如果用户使用他人代充或以其他违规方式充值造成其他用户或第三方权益受损时，不得因此要求卡片点点作任何补偿或赔偿，卡片点点保留随时冻结其帐户余额、暂停或终止其使用各项充值服务及禁用其帐户的权利。如卡片点点有理由相信用户的卡片点点积分帐户或使用情况涉及违规充值、作弊或异常状况，卡片点点有权拒绝该用户继续使用卡片点点积分，并按本协议采取冻结、禁用帐户等相关措施。
<br/>卡片点点积分一经充值成功，除法律法规明确规定外，在任何情况下不能兑换为法定货币，不能转让他人。用户间交易卡片点点积分构成对本协议的违反，卡片点点有权不通知用户而采取适当措施，以确保卡片点点不为违规用户提供卡片点点积分交易的平台服务。卡片点点积分不支持提现功能。
<br/>如果用户使用 iOS 设备充值卡片点点积分，必须通过 Apple 应用内购买机制（In-App Purchase）进行支付。为保障帐号安全，请在个人 iOS 设备上使用个人常用 Apple ID 进行操作。
<br/>iOS 和其他设备之间的卡片点点积分是独立计算不互通的。若使用同一帐号分别在 iOS 和其他设备上登录卡片点点时，卡片点点积分不能实现互通共用。使用 iOS 设备时购买的卡片点点积分，只能在 iOS 设备上使用，当使用其他设备登录同一帐号时，不能使用。
<br/>
<br/>侵权举报
<br/>
<br/>处理原则
<br/>
<br/>卡片点点作为知识讨论社区，高度重视自由表达和个人、企业正当权利的平衡。依照法律规定删除违法信息是卡片点点社区的法定义务，卡片点点社区亦未与任何中介机构合作开展此项业务。
<br/>
<br/>受理范围
<br/>
<br/>受理卡片点点社区内侵犯企业或个人合法权益的侵权举报，包括但不限于涉及个人隐私、造谣与诽谤、商业侵权。
<br/>
<br/>涉及个人隐私：发布内容中直接涉及身份信息，如个人姓名、家庭住址、身份证号码、工作单位、私人电话等详细个人隐私；
<br/>造谣、诽谤：发布内容中指名道姓（包括自然人和企业）的直接谩骂、侮辱、虚构中伤、恶意诽谤等；
<br/>商业侵权：泄露企业商业机密及其他根据保密协议不能公开讨论的内容。
<br/>
<br/>举报条件
<br/>
<br/>用户在卡片点点发表的内容仅表明其个人的立场和观点，并不代表卡片点点的立场或观点。如果个人或企业发现卡片点点上存在侵犯自身合法权益的内容，可以先尝试与作者取得联系，通过沟通协商解决问题。如您无法联系到作者，或无法通过与作者沟通解决问题，您可通过点击内容下方的举报按钮来向卡片点点平台进行投诉。为了保证问题能够及时有效地处理，请务必提交真实有效、完整清晰的材料，否则投诉将无法受理。您需要向卡片点点提供的投诉材料包括：
<br/>
<br/>权利人对涉嫌侵权内容拥有商标权、著作权和/或其他依法可以行使权利的权属证明，权属证明通常是营业执照或组织机构代码证；
<br/>举报人的身份证明，身份证明可以是身份证或护照；
<br/>如果举报人非权利人，请举报人提供代表权利人进行举报的书面授权证明。
<br/>为确保投诉材料的真实性，在侵权举报中，您还需要签署以下法律声明：
<br/>
<br/>我本人为所举报内容的合法权利人；
<br/>我举报的发布在卡片点点社区中的内容侵犯了本人相应的合法权益；
<br/>如果本侵权举报内容不完全属实，本人将承担由此产生的一切法律责任，并承担和赔偿卡片点点因根据投诉人的通知书对相关帐号的处理而造成的任何损失，包括但不限于卡片点点因向被投诉方赔偿而产生的损失及卡片点点名誉、商誉损害等。
<br/>
<br/>处理流程
<br/>
<br/>出于网络平台的监督属性，并非所有申请都必须受理。卡片点点自收到举报的七个工作日内处理完毕并给出回复。处理期间，不提供任何电话、邮件及其他方式的查询服务。
<br/>
<br/>出现卡片点点已经删除或处理的内容，但是百度、谷歌等搜索引擎依然可以搜索到的现象，是因为百度、谷歌等搜索引擎自带缓存，此类问题卡片点点无权也无法处理，因此相关申请不予受理。您可以自行联系搜索引擎服务商进行处理。
<br/>
<br/>此为卡片点点社区唯一的官方侵权投诉渠道，暂不提供其他方式处理此业务。
<br/>
<br/>用户在卡片点点中的商业行为引发的法律纠纷，由交易双方自行处理，与卡片点点无关。
<br/>
<br/>免责申明
<br/>
<br/>卡片点点不能对用户发表的回答或评论的正确性进行保证。
<br/>用户在卡片点点发表的内容仅表明其个人的立场和观点，并不代表卡片点点的立场或观点。作为内容的发表者，需自行对所发表内容负责，因所发表内容引发的一切纠纷，由该内容的发表者承担全部法律及连带责任。卡片点点不承担任何法律及连带责任。
<br/>卡片点点不保证网络服务一定能满足用户的要求，也不保证网络服务不会中断，对网络服务的及时性、安全性、准确性也都不作保证。
<br/>对于因不可抗力或卡片点点不能控制的原因造成的网络服务中断或其它缺陷，卡片点点不承担任何责任，但将尽力减少因此而给用户造成的损失和影响。
<br/>
<br/>协议修改
<br/>
<br/>根据互联网的发展和有关法律、法规及规范性文件的变化，或者因业务发展需要，卡片点点有权对本协议的条款作出修改或变更，一旦本协议的内容发生变动，卡片点点将会直接在卡片点点网站上公布修改之后的协议内容，该公布行为视为卡片点点已经通知用户修改内容。卡片点点也可采用电子邮件或私信的传送方式，提示用户协议条款的修改、服务变更、或其它重要事项。
<br/>如果不同意卡片点点对本协议相关条款所做的修改，用户有权并应当停止使用卡片点点。如果用户继续使用卡片点点，则视为用户接受卡片点点对本协议相关条款所做的修改。
				</p>
			</div>
			
		</div>
		
		
		
		<jsp:include page="foot.jsp" flush="true"/>
		
		
	</div>
</body>
</html>