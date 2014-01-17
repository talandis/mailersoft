<?php

    require_once dirname(__FILE__).'/base/ML_Rest.php';

    class ML_Subscribers extends ML_Rest
    {
        function ML_Subscribers( $api_key )
        {
            $this->name = 'subscribers';

            parent::__construct($api_key);
        }

        function add( $data = null, $resubscribe = 0 )
        {
            $data['resubscribe'] = $resubscribe;

            return $this->execute( 'POST', $data );
        }

        function addAll( $subscribers, $resubscribe = 0 )
        {
            $data['resubscribe'] = $resubscribe;

            $data['subscribers'] = $subscribers;

            return $this->execute( 'POST', $data, 'import' );
        }

        function get( $email = null, $history = 0 )
        {
            $this->setId( null );

            $data['email'] = $email;
            $data['history'] = $history;

            return $this->execute( 'GET', $data );
        }

        function remove( $email = null )
        {
            $data['email'] = $email;

            return $this->execute( 'DELETE', $data );
        }

        function unsubscribe( $email )
        {
            $data['email'] = $email;

            return $this->execute( 'POST', $data, 'unsubscribe' );
        }
    }

?>