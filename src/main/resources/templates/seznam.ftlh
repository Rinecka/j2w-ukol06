[#ftl]
[@c.page]
    <div class="seznam">
        [#list seznam as zaznam]
            <a href="/detail${zaznam.id}">
                <div class="vizitka">
                    <div class="jmeno">${zaznam.celeJmeno}</div>
                    <div class="firma">${zaznam.firma}</div>
                    <div class="udaje">
                        <div class="adresa">
                            <div>${zaznam.ulice}</div>
                            <div><span>${zaznam.psc}"</span><span>${zaznam.obec}</span></div>
                        </div>
                        <div class="kontakty">
                            [#if zaznam.email?has_content]
                                <div><i class="las la-at"></i> <span>${zaznam.email}</span></div>
                            [/#if]

                            [#if zaznam.telefon?has_content]
                                <div><i class="las la-phone-alt"></i> <span>${zaznam.telefon}</span></div>
                            [/#if]

                            [#if zaznam.web?has_content]
                                <div><i class="las la-globe"></i> <span>${zaznam.web}</span></div>
                            [/#if]
                        </div>
                    </div>
                </div>
            </a>
        [/#list]
        <a href="/nova">
            <div class="vizitka vizitka--nova">
                <svg width="100" height="100">
                    <rect width="20" height="100" x="40" y="0" fill="white"/>
                    <rect width="100" height="20" x="0" y="40" fill="white"/>
                </svg>
            </div>
        </a>
    </div>
[/@c.page]
