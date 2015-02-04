<jsp:include page="header.jsp"/>

<div class="hm-header-main-wrapper">
    <div class="container">
        <div class="hm-header-row clearfix">
            <div class="float-left hm-header-row-left">Customer Feedback Survey</div>
        </div>
    </div>
</div>


<div id="prof-container" data-agentId="${agentId}" data-customerEmailId="${customerEmailId}" class="prof-main-content-wrapper margin-top-25 margin-bottom-25">
    <div class="container">
        <div class="sq-ques-wrapper">
            <div quest-no="1" class="sq-quest-item">
                <div class="sq-top-img"></div>
                <div class="sq-main-txt">Survey Question</div>
                <div class="sq-bord-bot-sm"></div>
                <div class="sq-ques">
                    <i><span id="ques-text" class="sq-ques-txt"></span></i>
                </div>
                <div class="sq-rat-wrapper">
                    <div id="sq-stars" class="sq-star-wrapper clearfix" >
                        <div star-no="1" class="sq-star"></div>
                        <div star-no="2" class="sq-star"></div>
                        <div star-no="3" class="sq-star"></div>
                        <div star-no="4" class="sq-star"></div>
                        <div star-no="5" class="sq-star"></div>
                    </div>
                </div>
                <div class="sq-skip-main">
                    <div id="skip-ques" class="sq-skip-wrapper clearfix">
                        <div class="float-left sq-skip-chk st-checkbox-on hide"></div>
                        <div class="float-left sq-skip-chk st-checkbox-off"></div>
                        <div class="float-left sq-skip-txt"><i>Skip this question</i></div>
                    </div>
                </div>
                <div class="sq-np-wrapper clearfix">
                    <div id="prev" class="float-left sq-np-item sq-np-item-prev">&lt;&lt;&nbsp;&nbsp;&nbsp;Previous</div>
                    <div id="next" class="float-left sq-np-item sq-np-item-next">Next&nbsp;&nbsp;&nbsp;&gt;&gt;</div>
                </div>
                <div class="sq-btn-wrapper">
                    <div class="sq-btn-continue">Continue</div>
                </div>
            </div>
            <div quest-no="2" class="sq-quest-item hide">
                <div class="sq-top-img"></div>
                <div class="sq-main-txt">lorema ipsum lorema ipsum lorema ipsum</div>
                <div class="sq-bord-bot-sm"></div>
                <div class="sq-ques">
                    <i><span class="sq-ques-txt">lorem ipsum dore it ler. lorem ipsum dore it ler. lorem ipsum dore it ler. lorem ipsum dore it ler. lorem ipsum dore it ler. lorem ipsum dore it ler.</span></i>
                </div>
                <div class="sq-rat-wrapper">
                    <div class="sq-star-wrapper clearfix">
                        <div star-no="1" class="sq-star"></div>
                        <div star-no="2" class="sq-star"></div>
                        <div star-no="3" class="sq-star"></div>
                        <div star-no="4" class="sq-star"></div>
                        <div star-no="5" class="sq-star"></div>
                    </div>
                </div>
                <div class="sq-skip-main">
                    <div class="sq-skip-wrapper clearfix">
                        <div class="float-left sq-skip-chk st-checkbox-on hide"></div>
                        <div class="float-left sq-skip-chk st-checkbox-off"></div>
                        <div class="float-left sq-skip-txt"><i>Skip this question</i></div>
                    </div>
                </div>
                <div class="sq-np-wrapper clearfix">
                    <div class="float-left sq-np-item sq-np-item-prev">&lt;&lt;&nbsp;&nbsp;&nbsp;Previous</div>
                    <div class="float-left sq-np-item sq-np-item-next">Next&nbsp;&nbsp;&nbsp;&gt;&gt;</div>
                </div>
                <div class="sq-btn-wrapper">
                    <div class="sq-btn-continue">Continue</div>
                </div>
            </div>
            <div quest-no="3" class="sq-quest-item hide">
                <div class="sq-top-img"></div>
                <div class="sq-main-txt">lorema ipsum lorema ipsum lorema ipsum</div>
                <div class="sq-bord-bot-sm"></div>
                <div class="sq-ques">
                    <i><span class="sq-ques-txt">lorem ipsum dore it ler. lorem ipsum dore it ler. lorem ipsum dore it ler. lorem ipsum dore it ler. lorem ipsum dore it ler. lorem ipsum dore it ler.</span></i>
                </div>
                <div class="sq-rat-wrapper mgn-bot-40">
                    <div class="sq-slider-wrapper clearfix">
                        <div class="sq-slider-val">1</div>
                        <input type="range" min="1" max="10" step="1" value="1" data-rangeslider>
                    </div>
                </div>
                <div class="sq-skip-main">
                    <div class="sq-skip-wrapper clearfix">
                        <div class="float-left sq-skip-chk st-checkbox-on hide"></div>
                        <div class="float-left sq-skip-chk st-checkbox-off"></div>
                        <div class="float-left sq-skip-txt"><i>Skip this question</i></div>
                    </div>
                </div>
                <div class="sq-np-wrapper clearfix">
                    <div class="float-left sq-np-item sq-np-item-prev">&lt;&lt;&nbsp;&nbsp;&nbsp;Previous</div>
                    <div class="float-left sq-np-item sq-np-item-next sq-np-item-disabled">Next&nbsp;&nbsp;&nbsp;&gt;&gt;</div>
                </div>
                <div class="sq-btn-wrapper">
                    <div class="sq-btn-continue">Continue</div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="scripts.jsp"/>

<script>
    $(document).ready(function(){
        var survQuesNo = 1;
        var nextQ, prevQ;
        $('.st-checkbox-on').click(function(){
            $(this).hide();
            $(this).parent().find('.st-checkbox-off').show();
        });
        
        $('.st-checkbox-off').click(function(){
            $(this).hide();
            $(this).parent().find('.st-checkbox-on').show();
        });
        
        /*$('.sq-np-item-next').click(function(){
            if(!$(this).hasClass('sq-np-item-disabled')){
                survQuesNo = $(this).parent().parent().attr('quest-no');
                nextQ = parseInt(survQuesNo) + 1;
                $(this).parent().parent().hide();
                $(this).parent().parent().parent().find('div[quest-no="'+nextQ+'"]').show();
            }
        });
        
        $('.sq-np-item-prev').click(function(){
            if(!$(this).hasClass('sq-np-item-disabled')){
                survQuesNo = $(this).parent().parent().attr('quest-no');
                prevQ = parseInt(survQuesNo) - 1;
                $(this).parent().parent().hide();
                $(this).parent().parent().parent().find('div[quest-no="'+prevQ+'"]').show();
                survQuesNo = prevQ;
            }
        });
        */
        
               
        $('input[type="range"]').rangeslider({
            polyfill: false,

            // Default CSS classes
            rangeClass: 'rangeslider',
            fillClass: 'rangeslider__fill',
            handleClass: 'rangeslider__handle',

            onSlide: function(position, value) {
                $('div[quest-no="'+survQuesNo+'"]').find('.sq-slider-val').html(value);
            },
            
            // Callback function
            onSlideEnd: function(position, value) {
                $('div[quest-no="'+survQuesNo+'"]').find('.sq-slider-val').html(value);
            }
        });
        initSurvey($('#prof-container').attr("data-agentId"),$('#prof-container').attr("data-customerEmailId"));
    });
</script>

<jsp:include page="footer.jsp"/>